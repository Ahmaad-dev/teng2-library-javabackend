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

<<<<<<< HEAD
=======
    public void setAuthor(String author) {
        this.author = author;
    }

>>>>>>> f4bef0cc6630fd5a2ed18a772669fa371d22d8e9
    public String getIsbn() {
        return isbn;
    }

<<<<<<< HEAD
=======
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

>>>>>>> f4bef0cc6630fd5a2ed18a772669fa371d22d8e9
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
