package model;

public class Magazine extends MediaItem {
    public Magazine(String title, int copiesAvailable) {
        super(title, copiesAvailable);
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", copiesAvailable=" + getCopiesAvailable() +
                '}';
    }
}
