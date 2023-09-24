package com.example.demo.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookSearchService {
    List<Book> searchBooks(String query);

    default List<Book> searchBooksMixed(String query) {
        try {
            Long isbn = Long.parseLong(query);
            // If parsing succeeds, perform an exact match search for ISBN.
            return searchBooksByISBN(isbn);
        } catch (NumberFormatException e) {
            // If parsing fails, treat it as a string and perform a search for title, author, etc.
            return searchBooks(query);
        }
    }

    List<Book> searchBooksByISBN(Long isbn);

}
