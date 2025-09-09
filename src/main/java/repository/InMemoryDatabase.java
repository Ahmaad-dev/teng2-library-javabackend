package repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

import model.DVD;
import model.Magazine;
import model.Client;


@Component
public class InMemoryDatabase {
    public final Map<UUID, Book> books = new HashMap<>();
    public final Map<UUID, DVD> dvds = new HashMap<>();
    public final Map<UUID, Magazine> magazines = new HashMap<>();
    public final Map<UUID, Client> clients = new HashMap<>();

    @PostConstruct
    public void init() {
        // Bücher
        addBook(new Book("Der Herr der Ringe", "J.R.R. Tolkien", "978-3608939811", 3));
        addBook(new Book("Harry Potter", "J.K. Rowling", "978-3551551672", 2));
        addBook(new Book("1984", "George Orwell", "978-0451524935", 5));
        addBook(new Book("Fahrenheit 451", "Ray Bradbury", "978-0345342966", 4));
        addBook(new Book("Clean Code", "Robert C. Martin", "978-0132350884", 2));
        addBook(new Book("Effective Java", "Joshua Bloch", "978-0134685991", 3));
        addBook(new Book("The Pragmatic Programmer", "Andrew Hunt", "978-0201616224", 3));
        addBook(new Book("Thinking in Java", "Bruce Eckel", "978-0131872486", 1));
        addBook(new Book("Java für Einsteiger", "Uwe H. F. Sommer", "978-3446456342", 4));
        addBook(new Book("Spring in Action", "Craig Walls", "978-1617294945", 3));

        // DVDs
        addDVD(new DVD("Matrix", 5));
        addDVD(new DVD("Inception", 3));
        addDVD(new DVD("Interstellar", 2));
        addDVD(new DVD("The Dark Knight", 4));
        addDVD(new DVD("Shutter Island", 3));
        addDVD(new DVD("Avengers: Endgame", 2));
        addDVD(new DVD("Pulp Fiction", 2));
        addDVD(new DVD("Joker", 1));
        addDVD(new DVD("Fight Club", 4));
        addDVD(new DVD("Blade Runner", 2));

        // Magazine
        addMagazine(new Magazine("National Geographic", 3));
        addMagazine(new Magazine("TIME", 4));
        addMagazine(new Magazine("Scientific American", 2));
        addMagazine(new Magazine("Der Spiegel", 3));
        addMagazine(new Magazine("The Economist", 2));
        addMagazine(new Magazine("Nature", 1));
        addMagazine(new Magazine("GEO", 5));
        addMagazine(new Magazine("Focus", 3));
        addMagazine(new Magazine("PC Magazin", 2));
        addMagazine(new Magazine("LinuxUser", 4));

        // Clients
        addClient(new Client("Anna Meier"));
        addClient(new Client("Thomas Huber"));
        addClient(new Client("Lena Schmidt"));
        addClient(new Client("Max Mustermann"));
        addClient(new Client("Julia Berger"));
    }

    private void addBook(Book book) {
        books.put(book.getId(), book);
    }

    private void addDVD(DVD dvd) {
        dvds.put(dvd.getId(), dvd);
    }

    private void addMagazine(Magazine magazine) {
        magazines.put(magazine.getId(), magazine);
    }

    private void addClient(Client client) {
        clients.put(client.getId(), client);
    }
}
