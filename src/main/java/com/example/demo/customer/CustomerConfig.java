package com.example.demo.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository){
        return args -> {
            Customer despoina = new Customer(
                    "Dkrg02",
                    "1234567",
                    "Despoina Karagianni",
                    "deppie4@gmail.com",
                    "6940228039L",
                    "Ethnarchou Makariou 37",
                    "credit card"
            );

            Customer konstantinos = new Customer(
                    "Karkas",
                    "1214567",
                    "Konstantinos Karagiannis",
                    "konstantinoskrg@gmail.com",
                    "6979230479L",
                    "Ethnarchou Makariou 37",
                    "cash"
            );
            Customer stella = new Customer(
                    "stella",
                    "1214567",
                    "stella Anastasiou",
                    "Stellaanst@gmail.com",
                    "6979230479",
                    "hhjkh",
                    "cash"
            );


            repository.save(despoina);
            repository.save(konstantinos);
            repository.save(stella);
        };
    }
}
