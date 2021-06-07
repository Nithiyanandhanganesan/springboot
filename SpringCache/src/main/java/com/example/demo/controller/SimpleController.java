package com.example.demo.controller;

import com.example.demo.dto.Book;
import com.example.demo.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SimpleController {

    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/addbook")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping("/updatebook")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @GetMapping("/getbook/{id}")
    public Book getBook(@PathVariable int id){
        return bookService.getBook(id);
    }
    @DeleteMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable long id){
        return bookService.deleteBook(id);
    }

    @GetMapping("/cachetest")
    public List<Book> cachetest(){
        return bookService.getEmailByOtp();
    }
}
