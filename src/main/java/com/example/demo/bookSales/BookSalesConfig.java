package com.example.demo.bookSales;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookSalesConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookSalesRepository repository){
        return args -> {
            BookSales BookSale1 = new BookSales(
                    3L,
                    4L
            );

            BookSales BookSale2 = new BookSales(
                    4L,
                    9L
            );

            repository.saveAll(
                    List.of(BookSale1, BookSale2)
            );
        };
    }
}
