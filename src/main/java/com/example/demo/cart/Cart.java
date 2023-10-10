package com.example.demo.cart;
import com.example.demo.User.UserEntity;
import com.example.demo.book.Book;
import jakarta.persistence.*;

@Entity
@Table
public class Cart {
    @Id
    @SequenceGenerator(
            name = "cart_sequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private UserEntity user;

    private int quantity;


    public Cart() {
    }
    public Cart(Book book, int i) {
    }

    public Cart(Long id, Book book, UserEntity user, int quantity) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

    public Cart(Book book, UserEntity user, int quantity) {
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setCustomer(UserEntity user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", book=" + book +
                ", customer=" + user +
                ", quantity=" + quantity +
                '}';
    }
}