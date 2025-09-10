package controller;

<<<<<<< HEAD:src/main/java/service/LibraryService.java
import exception.ItemNotFoundException;
import exception.OutOfStockException;
import model.MediaItem;
import model.Unterklassen.*;
=======
import model.Unterklassen.Book;
import model.Unterklassen.DVD;
import model.Unterklassen.Magazine;
import model.Unterklassen.Client;
import model.MediaItem;


import exception.ItemNotFoundException;
import exception.OutOfStockException;

>>>>>>> f4bef0cc6630fd5a2ed18a772669fa371d22d8e9:src/main/java/controller/LibraryService.java
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
}
