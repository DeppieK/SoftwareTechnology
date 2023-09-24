package com.example.demo.questions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QuestionsConfig {
    @Bean
    CommandLineRunner commandLineRunner(QuestionsRepository repository){
        return args -> {
            Questions question1 = new Questions(
                    1L,
                    "This is a question",
                    3L,
                    "Author name"
            );

            Questions question2 = new Questions(
                    3L,
                    "This is another question",
                    7L,
                    "Another author name"
            );

            repository.saveAll(
                    List.of(question1, question2)
            );
        };
    }
}
