package com.example.demo.book;

import jakarta.persistence.*;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.demo")
@Entity
@Table
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )

    private Long id;
    private String title;
    private String genre;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Long ISBN;
    private int price;
    private boolean availability;
    private String publisher;
    private String author;
    private String photourl;

    public Book() {
    }

    public Book(Long id, String title, String genre, String description,Long ISBN, int price, boolean availability, String publisher, String author, String photourl) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.ISBN = ISBN;
        this.price = price;
        this.availability = availability;
        this.publisher = publisher;
        this.author = author;
        this.photourl = photourl;
    }

    public Book(String title, String genre, String description, Long ISBN, int price, boolean availability, String publisher, String author, String photourl) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.ISBN = ISBN;
        this.price = price;
        this.availability = availability;
        this.publisher = publisher;
        this.author = author;
        this.photourl = photourl;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", ISBN=" + ISBN +
                ", price=" + price +
                ", availability=" + availability +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                ", photourl='" + photourl + '\'' +
                '}';
    }
}