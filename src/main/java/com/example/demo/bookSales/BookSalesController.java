package com.example.demo.bookSales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bookSales")
public class BookSalesController {

    private final BookSalesService bookSalesService;

    @Autowired
    public BookSalesController(BookSalesService bookSalesService) {
        this.bookSalesService = bookSalesService;
    }

    @GetMapping()
    public List<BookSales> getBookSales(){
        return bookSalesService.getBookSales();
    }
}
