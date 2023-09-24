package com.example.demo.author;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AuthorConfig {
    @Bean
    CommandLineRunner commandLineRunner(AuthorRepository repository){
        return args -> {
            Author maria = new Author(
                    "maria1995",
                    "43232434",
                    "Maria Linou",
                    "mariaL@gmail.com",
                    "6982330198L"
            );

            Author Elena = new Author(
                    "ElenaK",
                    "45434345",
                    "Elena Konstantinidi",
                    "ElenaKon@gmail.com",
                    "6908446832L"

            );

            repository.saveAll(
                    List.of(maria, Elena)
            );
        };
    }
}
