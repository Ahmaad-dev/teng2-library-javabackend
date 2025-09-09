package model;

import java.util.UUID;

public abstract class MediaItem {
    protected UUID id;
    protected String title;
    protected int copiesAvailable;

    public MediaItem(String title, int copiesAvailable) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.copiesAvailable = copiesAvailable;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", title='" + title + ''' +
                ", copiesAvailable=" + copiesAvailable +
                '}';
    }
}
