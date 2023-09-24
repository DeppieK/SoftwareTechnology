package com.example.demo.cart;
import com.example.demo.book.Book;
import com.example.demo.customer.Customer;
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
    private Customer customer;

    private int quantity;


    public Cart() {
    }
    public Cart(Book book, int i) {
    }

    public Cart(Long id, Book book, Customer customer, int quantity) {
        this.id = id;
        this.book = book;
        this.customer = customer;
        this.quantity = quantity;
    }

    public Cart(Book book, Customer customer, int quantity) {
        this.book = book;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
                ", customer=" + customer +
                ", quantity=" + quantity +
                '}';
    }
}