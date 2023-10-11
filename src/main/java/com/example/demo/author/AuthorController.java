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

    public AuthorController(BookService bookService, BookSearchService bookSearchService, BookRepository bookRepository, CartService cartService, CommentsService commentsService, UserEntityService userEntityService) {
        this.bookService = bookService;
        this.bookSearchService = bookSearchService;
        this.bookRepository = bookRepository;
        this.cartService = cartService;
        this.commentsService = commentsService;
        this.userEntityService = userEntityService;
    }

    @ModelAttribute("baseURL")
    public String baseURL(@PathVariable("userId") Long userId) {
        return "/author/" + userId;
    }

    @GetMapping("/books")
    public String listBooks(@PathVariable("userId") Long userId, Model model) {
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
    public String addCommentToBook(@RequestParam("bookId") Long bookId, @RequestParam("commentText") String commentText) throws ChangeSetPersister.NotFoundException, ChangeSetPersister.NotFoundException {
        //commentsService.saveCommentForBook(bookId, commentText);

        // Redirect to the book's page or wherever you want to go after adding a comment
        return "redirect:author/{userId}/books/" + bookId;
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
    public String getUserDetails(@PathVariable("userId") Long userId, Model model) {
        UserEntity user = userEntityService.findUserById(userId); // Replace with your actual service method
        model.addAttribute("user", user);
        return "myAccount";
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
    @PostMapping("/addComment")
    public String addCommentToBook(@PathVariable("userId") Long userId,
                                   @RequestParam("bookId") Long bookId,
                                   @RequestParam("comment") String comment) throws ChangeSetPersister.NotFoundException {
        Book bookToAdd = bookService.findBookById(bookId);
        UserEntity userToAdd = userEntityService.findUserById(userId);
        commentsService.addComment(userToAdd, bookToAdd, comment);
        return "redirect:/author/{userId}/books/" + bookId;
    }
    @GetMapping("/removeFromCart")
    public String removeFromCart(@RequestParam("bookId") Long bookId) throws ChangeSetPersister.NotFoundException {
        Book bookToRemove = bookService.findBookById(bookId);
        cartService.removeFromCart(bookToRemove);

        return "redirect:/author/{userId}/cart";
    }

}
