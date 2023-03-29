package com.example.createapis.service;

import com.example.createapis.Entity.Book;
import com.example.createapis.repository.BookRepository;
import com.example.createapis.request.BookPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;
    public ResponseEntity<Book> addBook(Book book) {
        repository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    public ResponseEntity deleteBook(long id) {
        Optional<Book> book=repository.findById(id);
        if(book.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("content not found");
    }

    public ResponseEntity updateBook(long id, Book book) {

        Optional<Book> bookDb=repository.findById(id);
        if(bookDb.isPresent()){
            repository.deleteById(id);
            book.setId(id);
            repository.save(book);
            return ResponseEntity.status(HttpStatus.OK).body("updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("content not found");


    }
}
