package com.example.demo.cart;

import com.example.demo.User.UserEntity;
import com.example.demo.book.Book;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartItems(){
        return cartRepository.findAll();
    }


    public void addToCart(UserEntity userToAdd, Book bookToAdd, Integer quantity) {
        /* Cart existingCartItem = cartRepository.findByCustomerAndBook(customer, bookToAdd);*/
        Integer addedQuantity = quantity;
        Cart existingCartItem = cartRepository.findByBook(bookToAdd);
        if (existingCartItem != null) {
            if (existingCartItem.getUser().getId().equals(userToAdd.getId())) {
                // If the book is already in the cart and belongs to the same user, increase the quantity
                addedQuantity = existingCartItem.getQuantity() + quantity;
                existingCartItem.setQuantity(addedQuantity);
                cartRepository.save(existingCartItem);
            } else {
                Cart newCartItem = new Cart();
                newCartItem.setUser(userToAdd);
                newCartItem.setBook(bookToAdd);
                newCartItem.setQuantity(quantity);
                cartRepository.save(newCartItem);
            }
        } else {
            Cart newCartItem = new Cart();
            newCartItem.setUser(userToAdd);
            newCartItem.setBook(bookToAdd);
            newCartItem.setQuantity(quantity);
            cartRepository.save(newCartItem);
        }
    }
    public BigDecimal calculateTotalPrice(List<Cart> cartItems) {
        BigDecimal total = BigDecimal.ZERO;

        for (Cart cartItem : cartItems) {
            BigDecimal itemPrice = BigDecimal.valueOf(cartItem.getBook().getPrice());
            int quantity = cartItem.getQuantity();
            total = total.add(itemPrice.multiply(BigDecimal.valueOf(quantity)));
        }

        return total;
    }
    public void removeFromCart(Book bookToRemove) {
        Cart cartItem = cartRepository.findByBook(bookToRemove);

        if (cartItem != null) {
            cartRepository.delete(cartItem);
        }
    }
    public List<Cart> getCartItemsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

}
