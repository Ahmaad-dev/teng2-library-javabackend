package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    ArrayList<Book> books = new ArrayList<>();

    @PostMapping("/book")
    public void addBook(@RequestBody Book book) {
        books.add(book);
    }

    @GetMapping("/books")
    public ArrayList<Book> getBooks() {
        return books;
    }

    @GetMapping("/books/{isbn}")
    public Book getBooks(@PathVariable String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }
}
