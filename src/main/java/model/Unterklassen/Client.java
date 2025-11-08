package model.Unterklassen;

import model.MediaItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private List<MediaItem> borrowedItems;

    // Konstruktor mit nur Name (für Kompatibilität)
    public Client(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = null;
        this.phone = null;
        this.borrowedItems = new ArrayList<>();
    }

    // Konstruktor mit allen Feldern
    public Client(String name, String email, String phone) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.borrowedItems = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<MediaItem> getBorrowedItems() {
        return borrowedItems;
    }

    public boolean canBorrowMore() {
        return borrowedItems.size() < 5;
    }

    public void borrowItem(MediaItem item) {
        if (canBorrowMore()) {
            borrowedItems.add(item);
        } else {
            throw new IllegalStateException("Maximale Anzahl an ausgeliehenen Medien erreicht.");
        }
    }

    public void returnItem(MediaItem item) {
        borrowedItems.remove(item);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", borrowedItems=" + borrowedItems +
                '}';
    }
}
