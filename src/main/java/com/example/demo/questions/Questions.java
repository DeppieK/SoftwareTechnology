package com.example.demo.questions;

import jakarta.persistence.*;

@Entity
@Table
public class Questions {
    @Id
    @SequenceGenerator(
            name = "questions_sequence",
            sequenceName = "questions_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "questions_sequence"
    )
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String question;
    private Long book_id;
    private String author;

    public Questions() {
    }

    public Questions(Long id, String question, Long book_id, String author) {
        this.id = id;
        this.question = question;
        this.book_id = book_id;
        this.author = author;
    }

    public Questions(String question, Long book_id, String author) {
        this.question = question;
        this.book_id = book_id;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", book_id=" + book_id +
                ", author='" + author + '\'' +
                '}';
    }
}