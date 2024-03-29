package com.example.demo.controller;

import com.example.demo.dto.Book;
import com.example.demo.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SimpleController {

    @Autowired
    private BookServiceImpl bookService;


    @GetMapping("/check")
    public String checkStatus(@RequestParam(name = "tid") String trackNumber){
        return bookService.checkStatus(trackNumber);
    }

    @GetMapping("/check1")
    public String checkStatus1(@RequestParam(name = "tid") String trackNumber) {
        try {
            return bookService.checkStatus1(trackNumber);
        } catch(Exception e){
            System.out.println("cat bock");
            return "test";
        }
    }
}
