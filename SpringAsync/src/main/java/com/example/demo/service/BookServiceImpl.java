package com.example.demo.service;

import com.example.demo.dto.Book;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Thread.sleep;


@Service
public class BookServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);


    public String syncService() throws Exception {
        Thread.sleep(2000);
        System.out.println("calling sleep");
        return "completed";
    }

    @Async
    public String asyncService() throws Exception {
        Thread.sleep(2000);
        System.out.println("calling sleep");
        return "completed";
    }

}
