package controller;

import model.MediaItem;
import model.*;
import model.dto.CreateClientRequest;
import repository.InMemoryDatabase;
import service.LibraryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class LibraryController {

    private final LibraryService service;
    private final InMemoryDatabase db;

    public LibraryController(LibraryService service, InMemoryDatabase db) {
        this.service = service;
        this.db = db;
    }

    // ====================== Suchen ======================

    @GetMapping("/books/search")
    @CrossOrigin(origins = "*")
    public List<Book> searchBooks(@RequestParam String query) {
        return service.searchBooksByRegex(query);
    }

    @GetMapping("/dvds/search")
    @CrossOrigin(origins = "*")
    public List<DVD> searchDVDs(@RequestParam String query) {
        return service.searchDVDsByRegex(query);
    }

    @GetMapping("/magazines/search")
    @CrossOrigin(origins = "*")
    public List<Magazine> searchMagazines(@RequestParam String query) {
        return service.searchMagazinesByRegex(query);
    }

    @GetMapping("/books/isbn/{isbn}")
    @CrossOrigin(origins = "*")
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
    @CrossOrigin(origins = "*")
    public Collection<Book> getAllBooks() {
        return db.books.values();
    }

    @GetMapping("/dvds")
    @CrossOrigin(origins = "*")
    public Collection<DVD> getAllDVDs() {
        return db.dvds.values();
    }

    @GetMapping("/magazines")
    @CrossOrigin(origins = "*")
    public Collection<Magazine> getAllMagazines() {
        return db.magazines.values();
    }

    @GetMapping("/clients")
    public Collection<Client> getAllClients() {
        return db.clients.values();
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

    // ====================== Health Check für AWS ======================
    
    @GetMapping("/health")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> health = Map.of(
            "status", "UP",
            "service", "Library Management System",
            "timestamp", java.time.Instant.now().toString(),
            "version", "1.0.0"
        );
        return ResponseEntity.ok(health);
    }

    // ====================== Client Management ======================

    @PostMapping("/clients")
    public ResponseEntity<Map<String, Object>> createClient(@RequestBody CreateClientRequest request) {
        Map<String, Object> response = service.createClient(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Map<String, Object>> getClient(@PathVariable UUID clientId) {
        Map<String, Object> response = service.getClientById(clientId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/clients/{clientId}")
    public ResponseEntity<Map<String, Object>> updateClient(
            @PathVariable UUID clientId, 
            @RequestBody CreateClientRequest request) {
        Map<String, Object> response = service.updateClient(clientId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<Map<String, Object>> deleteClient(@PathVariable UUID clientId) {
        Map<String, Object> response = service.deleteClient(clientId);
        return ResponseEntity.ok(response);
    }
}
