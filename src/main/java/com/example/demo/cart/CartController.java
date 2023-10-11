package com.example.demo.cart;

import com.example.demo.User.UserEntity;
import com.example.demo.User.UserEntityService;
import com.example.demo.book.Book;
import com.example.demo.book.BookService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class CartController {


    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;
    private final UserEntityService userEntityService;

    public CartController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/cart")
    public String cartList(Model model) {
        List<Cart> cartItems = cartService.getCartItems();

        // Calculate the total price
        double totalPrice = cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getBook().getPrice())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice); // Add the total price to the model
        return "ShoppingCart";
    }
    @GetMapping("/checkout")
    public String cartCheckout(){
        return "checkout";
    }
    @GetMapping("/removeFromCart")
    public String removeFromCart(@RequestParam("bookId") Long bookId) throws ChangeSetPersister.NotFoundException {
        Book bookToRemove = bookService.findBookById(bookId);
        cartService.removeFromCart(bookToRemove);

        return "redirect:/cart";
    }


}
