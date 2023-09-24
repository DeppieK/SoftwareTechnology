package com.example.demo.book;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookrepository){
        return args -> {
            Book book1 = new Book(
                    "beauty and the beast",
                    "romance",
                    "belle falls in love with a beast",
                    123123123213213123L,
                    22,
                    true,
                    "Disney",
                    "author",
                    "test"
            );

            bookrepository.save(book1);
        };
    }
}
