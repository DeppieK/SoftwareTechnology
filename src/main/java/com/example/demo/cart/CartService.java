package com.example.demo.cart;

import com.example.demo.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartItems(){
        return cartRepository.findAll();
    }

    public void addToCart(Book bookToAdd, Integer quantity) {
        /* Cart existingCartItem = cartRepository.findByCustomerAndBook(customer, bookToAdd);*/
        Integer addedQuantity = quantity;
        Cart existingCartItem = cartRepository.findByBook(bookToAdd);
        if (existingCartItem != null) {
            // If the book is already in the cart, increase the quantity
            addedQuantity = existingCartItem.getQuantity() + quantity;
            existingCartItem.setQuantity(addedQuantity);
            cartRepository.save(existingCartItem);
        } else {
            // If the book is not in the cart, create a new cart item
            Cart newCartItem = new Cart();
            newCartItem.setBook(bookToAdd);
            newCartItem.setQuantity(quantity);
            cartRepository.save(newCartItem);
        }
    }
    public void removeFromCart(Book bookToRemove) {
        Cart cartItem = cartRepository.findByBook(bookToRemove);

        if (cartItem != null) {
            cartRepository.delete(cartItem);
        }
    }
    /*
    public void addToCart(/*Customer customer, Book bookToAdd) {
        // Check if the book is already in the customer's cart
       /* Cart existingCartItem = cartRepository.findByCustomerAndBook(customer, bookToAdd);
        Cart existingCartItem = cartRepository.findByBook(bookToAdd);
        if (existingCartItem != null) {
            // If the book is already in the cart, increase the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
        } else {
            // If the book is not in the cart, create a new cart item
            Cart newCartItem = new Cart(bookToAdd,/*, customer, 1);
            cartRepository.save(newCartItem);
        }
    }

    public Integer addToCart(Long bookId, Integer quantity /*, Customer customer){
        Book book = new Book();
        /*Cart cart = customer.getCart();
        if (cart == null){
            cart = new Cart();
        }

        Set<Cart> cartItems = (Set<Cart>) cartService.getCartItems();
        Cart cartItem = findCartItem(cartItems, book.getId());
        if (cartItems == null){
            cartItems = new HashSet<>();
            if (cartItem == null){
                cart = new Cart();
            }
        }
        return null;
    }

    private Cart findCartItem(Set<Cart> cartItems, Long bookId){
        if(cartItems ==  null){
            return null;
        }
        Cart cartItem = null;
        for(Cart book : cartItems){
            if(book.getBook().getId() == bookId){
                cartItem = book;
            }
        }
        return cartItem;
    }

    public Integer addToCart(Long bookId, Integer quantity /*, Customer customer){
        Integer addedQuantity = quantity;
        Book book = bookRepository.findById(bookId).get();
        /*CartItem cartItem = cartRepository.findByCustomerAndProduct(customer,product);
        if (cart != null){
            addedQuantity = cart.getQuantity() + quantity;
            cart.setQuantity(addedQuantity);
        }
        else{
            cart = new Cart();
            cart.setQuantity(quantity);
            /*cart.setCustomer(customer);
            cart.setBook(book);
        }
        cartRepository.save(cart);
        return addedQuantity;
    }
    public void addToCart(Customer customer, Book book) {
        // Check if the item is already in the cart for this customer
        // If it is, update the quantity; otherwise, create a new cart item
        Cart existingCartItem = cartRepository.findByCustomerAndBook(customer, book);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
        } else {
            Cart newCartItem = new Cart(book, customer, 1);
            cartRepository.save(newCartItem);
        }
    }


    public void addToCart(Book book) {
        // Check if the item is already in the cart
        // If it is, update the quantity; otherwise, create a new cart item
        Cart existingCartItem = cartRepository.findByBook(book);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
        } else {
            // You don't need the customer here since you mentioned no roles are involved
            // If you want to associate the cart item with a customer, you'll need to provide that information
            Cart newCartItem = new Cart(book, 1);
            cartRepository.save(newCartItem);
        }
    }*/
}
