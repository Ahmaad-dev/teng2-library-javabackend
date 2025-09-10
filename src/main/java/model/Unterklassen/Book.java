package model.Unterklassen;

import model.MediaItem;

public class Book extends MediaItem {
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn, int copiesAvailable) {
        super(title, copiesAvailable);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", copiesAvailable=" + getCopiesAvailable() +
                '}';
    }
}
