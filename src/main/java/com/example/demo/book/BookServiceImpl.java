package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class BookServiceImpl implements BookSearchService {
    private final BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> searchBooks(String query) {
        List<Book> books = bookRepository.searchBooks(query);
        return books;
    }
    @Override
    public List<Book> searchBooksByISBN(Long isbn) {
        return null;
    }

}
