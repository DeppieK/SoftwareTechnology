package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {



        // Add more query methods as needed

        @Query("SELECT b FROM Book b WHERE " +
                "LOWER(b.title) LIKE CONCAT('%', LOWER(:query), '%') OR " +
                "LOWER(b.author) LIKE CONCAT('%', LOWER(:query), '%') OR " +
                "LOWER(b.genre) LIKE CONCAT('%', LOWER(:query), '%') OR " +
                "LOWER(b.publisher) LIKE CONCAT('%', LOWER(:query), '%') OR " +
                "CAST(b.ISBN AS STRING) LIKE CONCAT('%', LOWER(:query), '%')")
        List<Book> searchBooks(@Param("query") String query);

    List<Book> findByISBN(Long isbn);
    List<Book> findByGenre(String genre);
    List<Book> findByAuthor(String author);
    List<Book> findByPublisher(String publisher);



}

