package controller;

import org.springframework.web.bind.annotation.*;
import repository.InMemoryDatabase;
import model.Unterklassen.DVD;
import model.Unterklassen.Magazine;
import model.Unterklassen.Client;
import model.Unterklassen.Book;
import controller.LibraryService;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

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
}
