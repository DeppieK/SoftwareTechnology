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


    //constructor
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

    //initializing the baseURL for this role
    @ModelAttribute("baseURL")
    public String baseURL() {
        return "";
    }

    //main page
    @GetMapping("/books")
    public String listBooks(Model model){
        List<Book> books = bookService.getBook();
        model.addAttribute("books", books);
        return "welcomePage";
    }

    //page with the book details
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

    //sign in form
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UserEntity user = userRepository.findByUsernameAndPassword(username, password);
        Long userId = user.getId();
        if (user != null) {
            if ("User".equals(user.getRole())){
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
    @GetMapping("/signIn")
    public String signIn() {

        return "signIn";
    }
    //sign up form
    @GetMapping("/signUp")
    public String signUp() {

        return "signUp";
    }

    //log out form
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
