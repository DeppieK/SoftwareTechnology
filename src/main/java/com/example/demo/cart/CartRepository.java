package com.example.demo.cart;

import com.example.demo.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository
        extends JpaRepository<Cart, Long> {
    /*
    List<Cart> findByCustomer(Customer customer);

    Cart findByCustomerAndBook(Customer customer, Book book);
    */
    Cart findByBook(Book book);


}
