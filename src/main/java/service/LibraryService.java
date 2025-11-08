package service;

import exception.ItemNotFoundException;
import exception.OutOfStockException;
import model.MediaItem;
import model.Unterklassen.*;
import repository.InMemoryDatabase;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final InMemoryDatabase db;

    public LibraryService(InMemoryDatabase db) {
        this.db = db;
    }

    // ========================= Suchen =========================

    public List<Book> searchBooksByRegex(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return db.books.values().stream()
                .filter(b -> pattern.matcher(b.getTitle()).find() || pattern.matcher(b.getAuthor()).find())
                .collect(Collectors.toList());
    }

    public List<DVD> searchDVDsByRegex(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return db.dvds.values().stream()
                .filter(d -> pattern.matcher(d.getTitle()).find())
                .collect(Collectors.toList());
    }

    public List<Magazine> searchMagazinesByRegex(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return db.magazines.values().stream()
                .filter(m -> pattern.matcher(m.getTitle()).find())
                .collect(Collectors.toList());
    }

    public Book findBookByIsbn(String isbn) {
        return db.books.values().stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Kein Buch mit ISBN " + isbn + " gefunden"));
    }

    // ========================= Ausleihe / Rückgabe =========================

    public void borrowItem(UUID clientId, UUID itemId) {
        Client client = db.clients.get(clientId);
        if (client == null) throw new ItemNotFoundException("Client nicht gefunden");

        if (!client.canBorrowMore()) {
            throw new RuntimeException("Client hat bereits 5 Medien ausgeliehen");
        }

        MediaItem item = findAnyMedia(itemId);

        if (item.getCopiesAvailable() <= 0) {
            throw new OutOfStockException("Keine Exemplare mehr verfügbar");
        }

        client.borrowItem(item);
        item.setCopiesAvailable(item.getCopiesAvailable() - 1);
    }

    public void returnItem(UUID clientId, UUID itemId) {
        Client client = db.clients.get(clientId);
        if (client == null) throw new ItemNotFoundException("Client nicht gefunden");

        MediaItem item = findAnyMedia(itemId);
        if (!client.getBorrowedItems().contains(item)) {
            throw new RuntimeException("Dieses Medium wurde von diesem Client nicht ausgeliehen");
        }

        client.returnItem(item);
        item.setCopiesAvailable(item.getCopiesAvailable() + 1);
    }

    // ========================= Helfer =========================

    private MediaItem findAnyMedia(UUID id) {
        if (db.books.containsKey(id)) return db.books.get(id);
        if (db.dvds.containsKey(id)) return db.dvds.get(id);
        if (db.magazines.containsKey(id)) return db.magazines.get(id);
        throw new ItemNotFoundException("Medium mit ID " + id + " nicht gefunden");
    }

    // ========================= Ausleihstatus prüfen =========================

    public List<MediaItem> getBorrowedItemsForClient(UUID clientId) {
        Client client = db.clients.get(clientId);
        if (client == null) throw new ItemNotFoundException("Client nicht gefunden");
        return client.getBorrowedItems();
    }

    public String getItemStatus(UUID itemId) {
        MediaItem item = findAnyMedia(itemId);
        int available = item.getCopiesAvailable();
        int borrowed = countBorrowedCopies(itemId);
        int total = available + borrowed;
        
        return String.format("Verfügbar: %d von %d Exemplaren (ausgeliehen: %d)", 
                           available, total, borrowed);
    }

    public int getItemAvailability(UUID itemId) {
        MediaItem item = findAnyMedia(itemId);
        return item.getCopiesAvailable();
    }

    public List<Client> getClientsBorrowingItem(UUID itemId) {
        MediaItem item = findAnyMedia(itemId);
        return db.clients.values().stream()
                .filter(client -> client.getBorrowedItems().contains(item))
                .collect(Collectors.toList());
    }

    public boolean isItemBorrowed(UUID itemId) {
        return !getClientsBorrowingItem(itemId).isEmpty();
    }

    private int countBorrowedCopies(UUID itemId) {
        MediaItem item = findAnyMedia(itemId);
        return (int) db.clients.values().stream()
                .flatMap(client -> client.getBorrowedItems().stream())
                .filter(borrowedItem -> borrowedItem.getId().equals(itemId))
                .count();
    }

    // ========================= Client Management =========================

    public Client createClient(String name, String email, String phone) {
        // Validierung: Name darf nicht leer sein
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name ist erforderlich und darf nicht leer sein");
        }
        
        // Validierung: Name muss eindeutig sein
        boolean nameExists = db.clients.values().stream()
                .anyMatch(client -> client.getName().equalsIgnoreCase(name.trim()));
        
        if (nameExists) {
            throw new IllegalArgumentException("Ein Kunde mit dem Namen '" + name.trim() + "' existiert bereits");
        }
        
        Client client = new Client(name.trim(), email != null ? email.trim() : null, phone != null ? phone.trim() : null);
        db.clients.put(client.getId(), client);
        return client;
    }

    public Client getClientById(UUID clientId) {
        Client client = db.clients.get(clientId);
        if (client == null) {
            throw new ItemNotFoundException("Client mit ID " + clientId + " nicht gefunden");
        }
        return client;
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(db.clients.values());
    }

    public Client updateClient(UUID clientId, String name, String email, String phone) {
        Client client = getClientById(clientId);
        
        // Wenn Name geändert wird, prüfen ob neuer Name bereits existiert
        if (name != null && !name.trim().isEmpty()) {
            String newName = name.trim();
            // Nur prüfen wenn sich der Name tatsächlich ändert
            if (!client.getName().equalsIgnoreCase(newName)) {
                boolean nameExists = db.clients.values().stream()
                        .anyMatch(c -> !c.getId().equals(clientId) && c.getName().equalsIgnoreCase(newName));
                
                if (nameExists) {
                    throw new IllegalArgumentException("Ein Kunde mit dem Namen '" + newName + "' existiert bereits");
                }
            }
            client.setName(newName);
        }
        
        if (email != null && !email.trim().isEmpty()) {
            client.setEmail(email.trim());
        }
        if (phone != null && !phone.trim().isEmpty()) {
            client.setPhone(phone.trim());
        }
        
        return client;
    }

    public void deleteClient(UUID clientId) {
        Client client = getClientById(clientId);
        
        // Prüfen ob noch Medien ausgeliehen sind
        if (!client.getBorrowedItems().isEmpty()) {
            throw new RuntimeException("Client kann nicht gelöscht werden - hat noch " + 
                client.getBorrowedItems().size() + " Medien ausgeliehen");
        }
        
        db.clients.remove(clientId);
    }
}
