package com.example.demo.bookSales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class BookSalesService {

    private final BookSalesRepository bookSalesRepository;

    @Autowired
    public BookSalesService(BookSalesRepository bookSalesRepository) {
        this.bookSalesRepository = bookSalesRepository;
    }

    public List<BookSales> getBookSales(){
        return bookSalesRepository.findAll();
    }
}
