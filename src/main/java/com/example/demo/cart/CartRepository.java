package com.example.demo.cart;

import com.example.demo.User.UserEntity;
import com.example.demo.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository
        extends JpaRepository<Cart, Long> {

    Cart findByBook(Book book);
    List<Cart> findByUserId(Long userId);


}
