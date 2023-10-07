package com.example.demo.cart;

import com.example.demo.book.Book;
import com.example.demo.book.BookService;
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
    /*
    @Autowired
    private CustomerService customerService;
    */

    @GetMapping("/cart")
    public String cartList(Model model) {
        List<Cart> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        return "ShoppingCart";
    }
    @GetMapping("/checkout")
    public String cartCheckout(){
        return "checkout";
    }
    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("bookId") Long bookId/*, HttpSession session*/,@RequestParam("quantity") Integer quantity) throws ChangeSetPersister.NotFoundException {
        Book bookToAdd = bookService.findBookById(bookId);
        /*Customer customer = (Customer) session.getAttribute("customer");*/
        cartService.addToCart(/*customer,*/ bookToAdd, quantity);

        return "redirect:/books/" + bookId;
    }
    @GetMapping("/removeFromCart")
    public String removeFromCart(@RequestParam("bookId") Long bookId) throws ChangeSetPersister.NotFoundException {
        Book bookToRemove = bookService.findBookById(bookId);
        cartService.removeFromCart(bookToRemove);

        return "redirect:/cart";
    }
    /*
    @GetMapping("/addToCart")
    public String viewShoppingCart() {
        return "ShoppingCart"; // Assuming "shoppingcart.html" is in your templates directory
    }
    /*
    @PostMapping("/addToCart/{bookId}")
    @ResponseBody // Indicates that the method returns a response directly
    public ResponseEntity<String> addToCart(@PathVariable Long bookId, HttpSession session) throws ChangeSetPersister.NotFoundException {
        // Retrieve the book based on the bookId (You need to implement this method)
        Book bookToAdd = bookService.findBookById(bookId);

        /*
        // Get the customer from the session (assuming you store it there)
        Customer customer = (Customer) session.getAttribute("customer");

        // Add the book to the cart
        cartService.addToCart(customer, bookToAdd);

        // Return a response indicating success
        return ResponseEntity.ok("Item added to cart");
    }
    @PostMapping("/addToCart/{bookId}")
    @ResponseBody // Indicates that the method returns a response directly
    public ResponseEntity<String> addToCart(@PathVariable Long bookId) throws ChangeSetPersister.NotFoundException {
        // Retrieve the book based on the bookId (You need to implement this method)
        Book bookToAdd = bookService.findBookById(bookId);

        // Optionally, you can identify the customer in some other way
        // For example, you might use a user ID stored in the session or a token

        // Add the book to the cart
        cartService.addToCart(bookToAdd);

        // Return a response indicating success
        return ResponseEntity.ok("Item added to cart");
    }
*/
}
