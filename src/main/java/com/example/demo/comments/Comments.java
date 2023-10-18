package com.example.demo.comments;

import com.example.demo.User.UserEntity;
import com.example.demo.book.Book;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table
public class Comments {
    @Id
    @SequenceGenerator(
            name = "comments_sequence",
            sequenceName = "comments_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comments_sequence"
    )

    private Long id;
    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private int stars;

    @Column(columnDefinition = "TEXT")
    private String comment;

    public Comments() {
    }

    public Comments(Long id, UserEntity user, Book book, int stars, String comment) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.stars = stars;
        this.comment = comment;
    }

    public Comments(UserEntity user, Book book, int stars, String comment) {
        this.user = user;
        this.book = book;
        this.stars = stars;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", stars=" + stars +
                ", comment='" + comment + '\'' +
                '}';
    }
}