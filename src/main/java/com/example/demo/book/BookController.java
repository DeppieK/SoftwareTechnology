package com.example.demo.book;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserEntityService;
import com.example.demo.User.UserRepository;
import com.example.demo.author.Author;
import com.example.demo.author.AuthorRepository;
import com.example.demo.cart.Cart;
import com.example.demo.cart.CartService;
import com.example.demo.comments.Comments;
import com.example.demo.comments.CommentsService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller

public class BookController {

    private final BookService bookService;
    private final BookSearchService bookSearchService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
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
            UserRepository userRepository, AuthorRepository authorRepository, CartService cartService,
            CommentsService commentsService,
            UserEntityService userEntityService) {
        this.bookService = bookService;
        this.bookSearchService = bookSearchService;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
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

    public String addCommentToBook(@RequestParam("bookId") Long bookId, @RequestParam("commentText") String commentText) throws ChangeSetPersister.NotFoundException, ChangeSetPersister.NotFoundException {
        //commentsService.saveCommentForBook(bookId, commentText);

        // Redirect to the book's page or wherever you want to go after adding a comment
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

        return "myAccount";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "signIn";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UserEntity user = userRepository.findByUsernameAndPassword(username, password);
        Long userId = user.getId();
        if (user != null) {
            if ("user".equals(user.getRole())){
                return "redirect:/user/" + userId + "/books";
            }
            else if ("Author".equals(user.getRole())){

                return "redirect:/author/" + userId + "/books";
            }
            else{
                return "redirect:/admin/" + userId + "/books";

            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "signIn";
        }
    }
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "signUp";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserEntity user, Model model) {
        Optional<UserEntity> existingUserByUsername = userRepository.findByUsername(user.getUsername());
        Optional<UserEntity> existingUserByEmail = userRepository.findByEmail(user.getEmail());

        if (existingUserByUsername.isPresent() || existingUserByEmail.isPresent()) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Username or email already exists. Please choose a different one.");
            System.out.println("Error");
            return "signUp";

        }else{
            System.out.println("Yes");
            userRepository.save(user);
            if ("Author".equals(user.getRole())) {
                Author author = new Author();
                author.setUser(user);
                authorRepository.save(author);
            }
        }
        System.out.println("Nice");
        return "redirect:/signIn";

    }

}
