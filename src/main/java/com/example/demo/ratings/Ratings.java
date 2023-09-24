package com.example.demo.ratings;

import jakarta.persistence.*;

@Entity
@Table
public class Ratings {
    @Id
    @SequenceGenerator(
            name = "ratings_sequence",
            sequenceName = "ratings_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ratings_sequence"
    )

    private Long id;
    private Long customer_id;
    private Long book_id;
    private int stars;

    @Column(columnDefinition = "TEXT")
    private String rating;

    public Ratings() {
    }

    public Ratings(Long id, Long customer_id, Long book_id, int stars, String rating) {
        this.id = id;
        this.customer_id = customer_id;
        this.book_id = book_id;
        this.stars = stars;
        this.rating = rating;
    }

    public Ratings(Long customer_id, Long book_id, int stars, String rating) {
        this.customer_id = customer_id;
        this.book_id = book_id;
        this.stars = stars;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", book_id=" + book_id +
                ", stars=" + stars +
                ", rating='" + rating + '\'' +
                '}';
    }
}