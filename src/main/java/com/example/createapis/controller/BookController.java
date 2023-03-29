package com.example.createapis.controller;

import com.example.createapis.Entity.Book;
import com.example.createapis.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookService service;
    @PostMapping("/add-book")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        log.info("request body in controller "+book.toString());
        return service.addBook(book);

    }

    @RequestMapping(value = "/delete{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteBook(@PathVariable long id){
        return service.deleteBook(id);
    }

    @RequestMapping(value = "/update{id}",method = RequestMethod.PUT)
    public ResponseEntity updateBook(@PathVariable long id,@RequestBody Book book){
        return service.updateBook(id,book);
    }


}
