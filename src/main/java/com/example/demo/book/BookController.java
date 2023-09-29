package com.example.demo.book;
import com.example.demo.cart.Cart;
import com.example.demo.cart.CartService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Controller
//@RestController
//@RequestMapping(path = "api/v1/books")
public class BookController {

    private BookService bookService;

    private BookSearchService bookSearchService;

    @Autowired
    private CartService cartService;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookService bookService, BookSearchService bookSearchService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookSearchService = bookSearchService;
        this.bookRepository = bookRepository;
    }
    @GetMapping("/books")
    public String listBooks(Model model){
        List<Book> books = bookService.getBook();
        model.addAttribute("books", books);
        return "welcomePage";
    }
    @GetMapping("/books/{bookId}")
    public String bookDetail(@PathVariable("bookId") long bookId, Model model) throws ChangeSetPersister.NotFoundException {
        Book book = bookService.findBookById(bookId);
        List<Cart> cartItems = cartService.getCartItems();

        model.addAttribute("book", book);
        model.addAttribute("cart", cartItems);
        return "bookDetails";
    }

    @GetMapping("books/search")
    public String searchBooks(@RequestParam(value = "query") String query, Model model){
        List<Book> books = bookSearchService.searchBooks(query);
        model.addAttribute("books",books);
        return "welcomePage";
    }
    @GetMapping("/books/isbn/{isbn}")
    public String findBooksByISBN(@PathVariable("isbn") Long isbn, Model model) {
        List<Book> books = bookRepository.findByISBN(isbn);
        model.addAttribute("books", books);
        return "welcomePage"; // Replace with the appropriate view name
    }



}