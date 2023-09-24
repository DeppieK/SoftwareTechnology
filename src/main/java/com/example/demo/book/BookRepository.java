package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%',:query,'%') OR b.author LIKE CONCAT('%',:query,'%') OR b.genre LIKE CONCAT('%',:query,'%') OR b.publisher LIKE CONCAT('%',:query,'%') OR CAST(b.ISBN AS STRING) LIKE CONCAT('%',:query,'%')")
    List<Book> searchBooks(@Param("query") String query);

    List<Book> findByISBN(Long isbn);


}

