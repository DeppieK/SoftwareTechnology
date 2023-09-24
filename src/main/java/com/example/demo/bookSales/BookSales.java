package com.example.demo.bookSales;

import jakarta.persistence.*;


@Entity
@Table
public class BookSales {
    @Id
    @SequenceGenerator(
            name = "bookSales_sequence",
            sequenceName = "bookSales_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bookSales_sequence"
    )

    private Long order_id;
    private Long book_id;

    public BookSales() {
    }

    public BookSales(Long order_id, Long book_id) {
        this.order_id = order_id;
        this.book_id = book_id;
    }

    public BookSales(Long book_id) {
        this.book_id = book_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "BookSales{" +
                "order_id=" + order_id +
                ", book_id=" + book_id +
                '}';
    }
}