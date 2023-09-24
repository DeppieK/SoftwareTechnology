package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBook(){
        return bookRepository.findAll();
    }

    public Book findBookById(Long bookId) throws ChangeSetPersister.NotFoundException {
        // Use the bookRepository to find the book by its ID
        // Assuming that your BookRepository has a method like findById
        // Replace BookRepository::findById with the actual method if needed
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            // Handle the case where the book is not found, e.g., throw an exception
            throw new ChangeSetPersister.NotFoundException();
        }
    }





}
