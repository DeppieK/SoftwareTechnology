package com.example.demo.ratings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository
        extends JpaRepository<Ratings, Long> {

}
