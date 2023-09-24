package com.example.demo.bookSales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSalesRepository
        extends JpaRepository<BookSales, Long> {

}
