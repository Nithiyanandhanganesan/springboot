package com.example.demo.service;

import com.example.demo.dto.Book;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;


@Service
public class BookServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    public static int test = 0;
    @Retryable(value = HttpStatusCodeException.class,maxAttempts = 3,
            backoff = @Backoff(3000),exclude = HttpClientErrorException.class)
    public String checkStatus(String trackingNumber){

        System.out.println("calling another service to get status");
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
       // return "approved";
    }

    @Recover
    public String recover(){
        return "Please try after some Time!!";
    }

    @Retryable(value = Exception.class,maxAttempts = 3,
            backoff = @Backoff(3000))
    public String checkStatus1(String trackingNumber) throws Exception{

            System.out.println("calling another service to get status:"+  ++test);
            if(test==3){
                return "working";
            }
            throw new Exception("test");


        // return "approved";
    }

}
