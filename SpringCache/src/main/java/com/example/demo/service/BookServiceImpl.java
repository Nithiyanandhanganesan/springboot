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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class BookServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    //@Autowired
   // private BookRepository bookRepository;

    public Book addBook(Book book) {
        logger.info("adding book with id - {}", book.getId());
        return book;
    }

    @CachePut(cacheNames = "books", key="#book.id")
    public Book updateBook(Book book) {
        //bookRepository.updateAddress(book.getId(), book.getName());
        logger.info("book updated with new name");
        book.setName("newname");
        return book;
    }

    @Cacheable(cacheNames = "books", key="#id")
    public Book getBook(int id) {
        logger.info("fetching book from db");
        Book book =  new Book();
        book.setId(id);
        book.setName("anand");
       return book;
    }

    @CacheEvict(cacheNames = "books", key = "#id")
    public String deleteBook(long id) {
       // bookRepository.deleteById(id);
        return "Book deleted";
    }

    @Autowired
    private CacheManager cacheManager;

    public List<Book> getEmailByOtp() {

        //1. Create a cache manager
        CacheManager cm = cacheManager.getInstance();

        //3. Get a cache called "cache1"
        Cache cache = cm.getCache("books");

        Book bok = new Book();
        bok.setId(25);
        cache.put(new Element(25,bok));
        //5. Get element from cache
        //Element ele = cache.get(1);
        Map<Object, Element> ele =  cache.getAll(cache.getKeys());

        List<Book> bk = new ArrayList<>();
        for(Map.Entry<Object, Element> temp : ele.entrySet()){
            bk.add((Book) temp.getValue().getObjectValue());
        }

        //6. Print out the element
        //String output = (ele == null ? null : ele.getObjectValue().toString());
        //Book output = (ele == null ? null : (Book) ele.getObjectValue());
        //System.out.println(output);
        /*//Ehcache cache = (Ehcache) cacheManager.getCache("books");
        Object value = null;
        for (Object key: cache.getKeys()) {
            Element element = cache.get(key);
            if (element != null) {
                value = element.getObjectValue();     // here is the value
                System.out.println(value.toString());
            }
        }
        return value.toString();*/
        return bk;
    }

   /* public boolean contains(String cacheName, Object o) {
        Cache cache = (Cache) CacheManager.ge  .getCache(cacheName).getNativeCache();
        for (Object key: cache.getKeys()) {
            Element element = cache.get(key);
            if (element != null && element.getObjectValue().equals(o)) {
                return true;
            }
        }
        return false;
    }*/

}
