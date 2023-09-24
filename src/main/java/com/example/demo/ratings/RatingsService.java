package com.example.demo.ratings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class RatingsService {

    private final RatingsRepository ratingsRepository;

    @Autowired
    public RatingsService(RatingsRepository ratingsRepository) {this.ratingsRepository = ratingsRepository;}

    public List<Ratings> getRatings(){
        return ratingsRepository.findAll();
    }
}
