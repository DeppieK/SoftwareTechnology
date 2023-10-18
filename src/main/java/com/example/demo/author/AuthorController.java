package com.example.demo.author;


import com.example.demo.User.UserEntity;
import com.example.demo.User.UserEntityService;
import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import com.example.demo.book.BookSearchService;
import com.example.demo.book.BookService;
import com.example.demo.cart.Cart;
import com.example.demo.cart.CartService;
import com.example.demo.comments.Comments;
import com.example.demo.comments.CommentsService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/author/{userId}")
public class AuthorController {

    private final BookService bookService;
    private final BookSearchService bookSearchService;
    private final BookRepository bookRepository;
    private final CartService cartService;
    private final CommentsService commentsService;
    private final UserEntityService userEntityService;

    //constructor
    public AuthorController(BookService bookService, BookSearchService bookSearchService, BookRepository bookRepository, CartService cartService, CommentsService commentsService, UserEntityService userEntityService) {
        this.bookService = bookService;
        this.bookSearchService = bookSearchService;
        this.bookRepository = bookRepository;
        this.cartService = cartService;
        this.commentsService = commentsService;
        this.userEntityService = userEntityService;
    }

    //initializing the baseURL for this role
    @ModelAttribute("baseURL")
    public String baseURL(@PathVariable("userId") Long userId) {
        return "/author/" + userId;
    }

    //main page
    @GetMapping("/books")
    public String listBooks(@PathVariable("userId") Long userId, Model model) {
        List<Book> books = bookService.getBook();
        model.addAttribute("books", books);
        return "welcomePage";
    }

    //page with the book details
    @GetMapping("/books/{bookId}")
    public String bookDetail(@PathVariable("bookId") long bookId, Model model) throws ChangeSetPersister.NotFoundException {
        Book book = bookService.findBookById(bookId);

        List<Cart> cartItems = cartService.getCartItems();
        List<Comments> comments = commentsService.getCommentsByBookId(bookId);

        model.addAttribute("book", book);
        model.addAttribute("cart", cartItems);
        model.addAttribute("comments", comments);

        return "bookDetails";
    }
    //add a new comment
    @PostMapping("/addComment")
    public String addCommentToBook(@PathVariable("userId") Long userId,
                                   @RequestParam("bookId") Long bookId,
                                   @RequestParam("comment") String comment) throws ChangeSetPersister.NotFoundException {
        Book bookToAdd = bookService.findBookById(bookId);
        UserEntity userToAdd = userEntityService.findUserById(userId);
        commentsService.addComment(userToAdd, bookToAdd, comment);
        return "redirect:/author/{userId}/books/" + bookId;
    }

    //search method
    @GetMapping("books/search")
    public String searchBooks(@RequestParam(value = "query") String query, Model model){
        List<Book> books = bookSearchService.searchBooks(query);
        model.addAttribute("books",books);
        return "genres";
    }

    //search books based on isbn
    @GetMapping("/books/isbn/{isbn}")
    public String findBooksByISBN(@PathVariable("isbn") Long isbn, Model model) {
        List<Book> books = bookRepository.findByISBN(isbn);
        model.addAttribute("books", books);
        return "welcomePage";
    }

    //display books based on a specific genre
    @GetMapping("/books/genre/{genre}")
    public String findBooksByGenre(@PathVariable("genre") String genre, Model model) {
        List<Book> books = bookRepository.findByGenre(genre);
        model.addAttribute("books", books);
        return "genres";
    }

    //display books based on a specific author
    @GetMapping("/books/author/{author}")
    public String findBooksByAuthor(@PathVariable("author") String author, Model model) {
        List<Book> books = bookRepository.findByAuthor(author);
        model.addAttribute("books", books);
        return "genres";
    }

    //display books based on a specific publisher
    @GetMapping("/books/publisher/{publisher}")
    public String findBooksByPublisher(@PathVariable("publisher") String publisher, Model model) {
        List<Book> books = bookRepository.findByPublisher(publisher);
        model.addAttribute("books", books);
        return "genres";
    }

    //log out form
    @GetMapping("/logout")
    public String logout() {

        return "redirect:/books";
    }

    //user details page
    @GetMapping("/myAccount")
    public String getUserDetails(@PathVariable("userId") Long userId, Model model) {
        UserEntity user = userEntityService.findUserById(userId);
        model.addAttribute("user", user);
        return "myAccount";
    }


    //cart page
    @GetMapping("/cart")
    public String cartList(Model model) {
        List<Cart> cartItems = cartService.getCartItems();

        double totalPrice = cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getBook().getPrice())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice); // Add the total price to the model
        return "ShoppingCart";
    }

    //add item to cart
    public String addToCart(
            @PathVariable("userId") Long userId,
            @RequestParam("bookId") Long bookId,
            @RequestParam("quantity") Integer quantity) throws ChangeSetPersister.NotFoundException {
        Book bookToAdd = bookService.findBookById(bookId);
        UserEntity userToAdd = userEntityService.findUserById(userId);
        /*Customer customer = (Customer) session.getAttribute("customer");*/
        cartService.addToCart(userToAdd, bookToAdd, quantity);

        return "redirect:/author/{userId}/books/" + bookId;
    }

    //remove an item from cart
    @GetMapping("/removeFromCart")
    public String removeFromCart(@RequestParam("bookId") Long bookId) throws ChangeSetPersister.NotFoundException {
        Book bookToRemove = bookService.findBookById(bookId);
        cartService.removeFromCart(bookToRemove);

        return "redirect:/author/{userId}/cart";
    }
    //checkout page
    @GetMapping("/checkout")
    public String cartCheckout(){
        return "checkout";
    }



}
