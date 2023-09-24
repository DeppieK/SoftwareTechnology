package com.example.demo.orders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OrdersConfig {
    @Bean
    CommandLineRunner commandLineRunner(OrdersRepository repository){
        return args -> {
            Orders order1 = new Orders(
                    3L,
                    20
            );

            Orders order2 = new Orders(
                    4L,
                    35
            );

            repository.saveAll(
                    List.of(order1, order2)
            );
        };
    }
}
