package com.example.demo.ratings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RatingsConfig {
    @Bean
    CommandLineRunner commandLineRunner(RatingsRepository repository){
        return args -> {
            Ratings rating1 = new Ratings(
                    4L,
                    5L,
                    7L,
                    4,
                    "really good book!"
            );

            Ratings rating2 = new Ratings(
                    8L,
                    2L,
                    2L,
                    2,
                    "too romantic for my taste"
            );

            repository.saveAll(
                    List.of(rating1, rating2)
            );
        };
    }
}
