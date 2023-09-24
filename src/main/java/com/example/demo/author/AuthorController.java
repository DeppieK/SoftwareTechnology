package com.example.demo.author;


import com.example.demo.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
//@RequestMapping(path = "api/v1/author")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorService authorService, AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    @GetMapping()
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }
}
