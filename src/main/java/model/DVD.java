package model;

public class DVD extends MediaItem {
    public DVD(String title, int copiesAvailable) {
        super(title, copiesAvailable);
    }

    @Override
    public String toString() {
        return "DVD{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", copiesAvailable=" + getCopiesAvailable() +
                '}';
    }
}
