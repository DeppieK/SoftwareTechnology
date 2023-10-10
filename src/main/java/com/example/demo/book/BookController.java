package com.example.demo.book;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserEntityService;
import com.example.demo.cart.Cart;
import com.example.demo.cart.CartService;
import com.example.demo.comments.Comments;
import com.example.demo.comments.CommentsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.xml.stream.events.Comment;
import java.util.List;
@Controller
//@RestController
//@RequestMapping(path = "api/v1/books")
public class BookController {

    private final BookService bookService;
    private final BookSearchService bookSearchService;
    private final BookRepository bookRepository;
    private final CartService cartService;
    private final CommentsService commentsService;
    private final UserEntityService userEntityService;

    @ModelAttribute("baseURL")
    public String baseURL() {
        return "";
    }
    @Autowired
    public BookController(
            BookService bookService,
            BookSearchService bookSearchService,
            BookRepository bookRepository,
            CartService cartService,
            CommentsService commentsService,
            UserEntityService userEntityService) {
        this.bookService = bookService;
        this.bookSearchService = bookSearchService;
        this.bookRepository = bookRepository;
        this.cartService = cartService;
        this.commentsService = commentsService;
        this.userEntityService = userEntityService;
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
        //Long userId = commentsService.getUserIdByBookId(bookId); // Updated to Long

        List<Cart> cartItems = cartService.getCartItems();
        List<Comments> comments = commentsService.getCommentsByBookId(bookId);

        model.addAttribute("book", book);
        model.addAttribute("cart", cartItems);
        model.addAttribute("comments", comments);
        //model.addAttribute("user", userId);

        return "bookDetails";
    }
    @PostMapping("/books/addComment")
    public String addComment(@RequestParam Long bookId, @RequestParam String commentText) {
        // Retrieve the book based on the bookId (you'll need to implement this)
        Book book = bookService.getBookById(bookId);

        // Create a new Comment object
        Comments comments = new Comments();
        comments.setBook(book);
        comments.setComment(commentText);

        // Assuming you have a User object for the current user
        // You can set the user who posted the comment here

        // Save the comment using your CommentService
        commentsService.saveComment(comments);

        // Redirect back to the book details page or wherever you want
        return "redirect:/books/" + bookId;
    }


    @GetMapping("books/search")
    public String searchBooks(@RequestParam(value = "query") String query, Model model){
        List<Book> books = bookSearchService.searchBooks(query);
        model.addAttribute("books",books);
        return "genres";
    }
    @GetMapping("/books/isbn/{isbn}")
    public String findBooksByISBN(@PathVariable("isbn") Long isbn, Model model) {
        List<Book> books = bookRepository.findByISBN(isbn);
        model.addAttribute("books", books);
        return "welcomePage"; // Replace with the appropriate view name
    }
    @GetMapping("/books/genre/{genre}")
    public String findBooksByGenre(@PathVariable("genre") String genre, Model model) {
        List<Book> books = bookRepository.findByGenre(genre);
        model.addAttribute("books", books);
        return "genres"; // Replace with the appropriate view name
    }
    @GetMapping("/books/author/{author}")
    public String findBooksByAuthor(@PathVariable("author") String author, Model model) {
        List<Book> books = bookRepository.findByAuthor(author);
        model.addAttribute("books", books);
        return "genres"; // Replace with the appropriate view name
    }
    @GetMapping("/books/publisher/{publisher}")
    public String findBooksByPublisher(@PathVariable("publisher") String publisher, Model model) {
        List<Book> books = bookRepository.findByPublisher(publisher);
        model.addAttribute("books", books);
        return "genres"; // Replace with the appropriate view name
    }
    @GetMapping("/signIn")
    public String signIn() {

        return "signIn";
    }
    @GetMapping("/signUp")
    public String signUp() {

        return "signUp";
    }
    @GetMapping("/logout")
    public String logout() {

        return "redirect:/books";
    }
    @GetMapping("/myAccount")
    public String myAccount() {

        return "myAcoount";
    }



}
