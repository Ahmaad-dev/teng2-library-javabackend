package controller;

import model.MediaItem;
import model.Unterklassen.*;
import model.dto.CreateClientRequest;
import repository.InMemoryDatabase;
import service.LibraryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class LibraryController {

    private final LibraryService service;
    private final InMemoryDatabase db;

    public LibraryController(LibraryService service, InMemoryDatabase db) {
        this.service = service;
        this.db = db;
    }

    // ====================== Suchen ======================

    @GetMapping("/books/search")
    public List<Book> searchBooks(@RequestParam String query) {
        return service.searchBooksByRegex(query);
    }

    @GetMapping("/dvds/search")
    public List<DVD> searchDVDs(@RequestParam String query) {
        return service.searchDVDsByRegex(query);
    }

    @GetMapping("/magazines/search")
    public List<Magazine> searchMagazines(@RequestParam String query) {
        return service.searchMagazinesByRegex(query);
    }

    @GetMapping("/books/isbn/{isbn}")
    public Book findBookByIsbn(@PathVariable String isbn) {
        return service.findBookByIsbn(isbn);
    }

    // ====================== Ausleihe & Rückgabe ======================

    @PostMapping("/clients/{clientId}/borrow/{itemId}")
    public String borrow(@PathVariable UUID clientId, @PathVariable UUID itemId) {
        service.borrowItem(clientId, itemId);
        return "Medium erfolgreich ausgeliehen.";
    }

    @PostMapping("/clients/{clientId}/return/{itemId}")
    public String giveBack(@PathVariable UUID clientId, @PathVariable UUID itemId) {
        service.returnItem(clientId, itemId);
        return "Medium erfolgreich zurückgegeben.";
    }

    // ====================== Datenübersicht ======================

    @GetMapping("/books")
    public Collection<Book> getAllBooks() {
        return db.books.values();
    }

    @GetMapping("/dvds")
    public Collection<DVD> getAllDVDs() {
        return db.dvds.values();
    }

    @GetMapping("/magazines")
    public Collection<Magazine> getAllMagazines() {
        return db.magazines.values();
    }

    @GetMapping("/clients")
    public Collection<Client> getAllClients() {
        return db.clients.values();
    }

    // ====================== Kunden-Management ======================

    @PostMapping("/clients")
    public ResponseEntity<Map<String, Object>> createClient(@RequestBody CreateClientRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client newClient = service.createClient(request.getName(), request.getEmail(), request.getPhone());
            response.put("success", true);
            response.put("message", "Kunde '" + newClient.getName() + "' wurde erfolgreich erstellt!");
            response.put("client", newClient);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Unerwarteter Fehler beim Erstellen des Kunden: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Map<String, Object>> getClientById(@PathVariable UUID clientId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client client = service.getClientById(clientId);
            response.put("success", true);
            response.put("message", "Kunde erfolgreich gefunden");
            response.put("client", client);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Kunde nicht gefunden: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/clients/{clientId}")
    public ResponseEntity<Map<String, Object>> updateClient(@PathVariable UUID clientId, @RequestBody CreateClientRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client updatedClient = service.updateClient(clientId, request.getName(), request.getEmail(), request.getPhone());
            response.put("success", true);
            response.put("message", "Kunde '" + updatedClient.getName() + "' wurde erfolgreich aktualisiert!");
            response.put("client", updatedClient);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Fehler beim Aktualisieren: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<Map<String, Object>> deleteClient(@PathVariable UUID clientId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client client = service.getClientById(clientId); // Um den Namen für die Nachricht zu erhalten
            service.deleteClient(clientId);
            response.put("success", true);
            response.put("message", "Kunde '" + client.getName() + "' wurde erfolgreich gelöscht!");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            if (e.getMessage().contains("hat noch") && e.getMessage().contains("ausgeliehen")) {
                response.put("message", "Kunde kann nicht gelöscht werden - es sind noch Medien ausgeliehen");
            } else {
                response.put("message", "Kunde nicht gefunden: " + e.getMessage());
            }
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ====================== Ausleihstatus prüfen ======================

    @GetMapping("/clients/{clientId}/borrowed-items")
    public List<MediaItem> getBorrowedItems(@PathVariable UUID clientId) {
        return service.getBorrowedItemsForClient(clientId);
    }

    @GetMapping("/items/{itemId}/status")
    public String getItemStatus(@PathVariable UUID itemId) {
        return service.getItemStatus(itemId);
    }

    @GetMapping("/items/{itemId}/availability")
    public int getItemAvailability(@PathVariable UUID itemId) {
        return service.getItemAvailability(itemId);
    }

    @GetMapping("/items/{itemId}/borrower")
    public List<Client> getItemBorrowers(@PathVariable UUID itemId) {
        return service.getClientsBorrowingItem(itemId);
    }
}
