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


    @GetMapping("/sync")
    public String synctest() throws Exception{
        String output = bookService.syncService();
        return "sync method completed";
    }

    @GetMapping("/async")
    public String asynctest() throws Exception{
        String output = bookService.asyncService();
        return "async method completed";
    }
}
