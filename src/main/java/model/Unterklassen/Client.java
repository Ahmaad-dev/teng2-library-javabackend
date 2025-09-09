package model.Unterklassen;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private List<MediaItem> borrowedItems;

    public Client(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MediaItem> getBorrowedItems() {
        return borrowedItems;
    }

    public boolean canBorrowMore() {
        return borrowedItems.size() < 5;
    }

    public void borrowItem(MediaItem item) {
        borrowedItems.add(item);
    }

    public void returnItem(MediaItem item) {
        borrowedItems.remove(item);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + ''' +
        ", borrowedItems=" + borrowedItems +
                '}';
    }
}
