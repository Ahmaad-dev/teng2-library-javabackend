package service;

import exception.ItemNotFoundException;
import exception.OutOfStockException;
import model.MediaItem;
import model.*;
import model.dto.CreateClientRequest;
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
        // Keine Notwendigkeit das Item zu laden, da wir nur die IDs vergleichen
        return (int) db.clients.values().stream()
                .flatMap(client -> client.getBorrowedItems().stream())
                .filter(borrowedItem -> borrowedItem.getId().equals(itemId))
                .count();
    }

    // ====================== Client Management ======================

    public Map<String, Object> createClient(CreateClientRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Validierung
            if (request.getName() == null || request.getName().trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Name ist erforderlich");
                return response;
            }

            // Prüfung auf doppelte Namen (case-insensitive)
            boolean nameExists = db.clients.values().stream()
                    .anyMatch(client -> client.getName().equalsIgnoreCase(request.getName().trim()));
            
            if (nameExists) {
                response.put("success", false);
                response.put("message", "Ein Kunde mit diesem Namen existiert bereits");
                return response;
            }

            // Client erstellen
            Client newClient = new Client(
                request.getName().trim(),
                request.getEmail() != null ? request.getEmail().trim() : null,
                request.getPhone() != null ? request.getPhone().trim() : null
            );

            db.clients.put(newClient.getId(), newClient);

            response.put("success", true);
            response.put("message", "Kunde wurde erfolgreich erstellt");
            response.put("data", newClient);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Fehler beim Erstellen des Kunden: " + e.getMessage());
        }

        return response;
    }

    public Map<String, Object> getClientById(UUID clientId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Client client = db.clients.get(clientId);
            if (client == null) {
                response.put("success", false);
                response.put("message", "Kunde nicht gefunden");
                return response;
            }

            response.put("success", true);
            response.put("message", "Kunde gefunden");
            response.put("data", client);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Fehler beim Abrufen des Kunden: " + e.getMessage());
        }

        return response;
    }

    public Map<String, Object> updateClient(UUID clientId, CreateClientRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Client client = db.clients.get(clientId);
            if (client == null) {
                response.put("success", false);
                response.put("message", "Kunde nicht gefunden");
                return response;
            }

            // Name aktualisieren (falls angegeben)
            if (request.getName() != null && !request.getName().trim().isEmpty()) {
                // Prüfung auf doppelte Namen (ausgenommen der aktuelle Client)
                boolean nameExists = db.clients.values().stream()
                        .anyMatch(c -> !c.getId().equals(clientId) && 
                                 c.getName().equalsIgnoreCase(request.getName().trim()));
                
                if (nameExists) {
                    response.put("success", false);
                    response.put("message", "Ein anderer Kunde mit diesem Namen existiert bereits");
                    return response;
                }
                client.setName(request.getName().trim());
            }

            // E-Mail aktualisieren (falls angegeben)
            if (request.getEmail() != null) {
                client.setEmail(request.getEmail().trim());
            }

            // Telefon aktualisieren (falls angegeben)
            if (request.getPhone() != null) {
                client.setPhone(request.getPhone().trim());
            }

            response.put("success", true);
            response.put("message", "Kunde wurde erfolgreich aktualisiert");
            response.put("data", client);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Fehler beim Aktualisieren des Kunden: " + e.getMessage());
        }

        return response;
    }

    public Map<String, Object> deleteClient(UUID clientId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Client client = db.clients.get(clientId);
            if (client == null) {
                response.put("success", false);
                response.put("message", "Kunde nicht gefunden");
                return response;
            }

            // Prüfung, ob Kunde noch Medien ausgeliehen hat
            if (!client.getBorrowedItems().isEmpty()) {
                response.put("success", false);
                response.put("message", "Kunde kann nicht gelöscht werden: " + 
                           client.getBorrowedItems().size() + " Medien noch ausgeliehen");
                return response;
            }

            db.clients.remove(clientId);

            response.put("success", true);
            response.put("message", "Kunde wurde erfolgreich gelöscht");
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Fehler beim Löschen des Kunden: " + e.getMessage());
        }

        return response;
    }
}
