package repository;

import model.MediaItem;
import model.Unterklassen.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;
import model.Unterklassen.Client;


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
        addDVD(new DVD("Matrix", "Wachowski", 1999, 5));
        addDVD(new DVD("Inception", "Nolan", 2010, 3));
        addDVD(new DVD("Interstellar", "Nolan", 2014, 2));
        addDVD(new DVD("The Dark Knight", "Nolan", 2008, 4));
        addDVD(new DVD("Shutter Island", "Scorsese", 2010, 3));
        addDVD(new DVD("Avengers: Endgame", "Russo Brothers", 2019, 2));
        addDVD(new DVD("Pulp Fiction", "Tarantino", 1994, 2));
        addDVD(new DVD("Joker", "Phillips", 2019, 1));
        addDVD(new DVD("Fight Club", "Fincher", 1999, 4));
        addDVD(new DVD("Blade Runner", "Scott", 1982, 2));


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

        // Clients mit realistischen E-Mail-Adressen und Telefonnummern
        addClient(new Client("Anna Meier", "anna.meier@email.de", "+49 30 12345678"));
        addClient(new Client("Thomas Huber", "thomas.huber@gmail.com", "+49 89 87654321"));
        addClient(new Client("Lena Schmidt", "lena.schmidt@web.de", "+49 40 11223344"));
        addClient(new Client("Max Mustermann", "max.mustermann@example.com", "+49 69 55667788"));
        addClient(new Client("Julia Berger", "julia.berger@yahoo.de", "+49 221 99887766"));
        addClient(new Client("Stefan Wagner", "stefan.wagner@outlook.de", "+49 711 44332211"));
        addClient(new Client("Maria Kaufmann", "maria.kaufmann@t-online.de", "+49 511 66778899"));
        addClient(new Client("Daniel Klein", "daniel.klein@freenet.de", "+49 341 22446688"));
        addClient(new Client("Sarah Hoffmann", "sarah.hoffmann@gmx.de", "+49 201 13579246"));
        addClient(new Client("Michael Richter", "michael.richter@arcor.de", "+49 351 98765432"));
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
