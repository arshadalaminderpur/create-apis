package com.example.createapis.service;

import com.example.createapis.model.Book;
import com.example.createapis.repository.BookRepository;
import com.example.createapis.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public  ResponseEntity<Response> updateBookQuantity(long id, Book bookRequest) {

        Optional<Book> bookOptional=repository.findById(id);
        Response response=new Response();
        if(!bookOptional.isPresent()){
            response.setStatus("Book not found with this id");
            response.setMessage("please enter a valid book id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Book book=bookOptional.get();
        book.setNoCopies(bookRequest.getNoCopies());
        repository.save(book);
        response.setStatus("SUCCESSFULL");
        response.setMessage("Book quantity updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);


    }

    public  ResponseEntity<Response> updateBookPrice(long id, Book bookRequest) {

        Optional<Book> bookOptional=repository.findById(id);
        Response response=new Response();
        if(!bookOptional.isPresent()){
            response.setStatus("Book not found with this id");
            response.setMessage("please enter a valid book id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Book book=bookOptional.get();
        book.setPrice(bookRequest.getPrice());
        repository.save(book);
        response.setStatus("SUCCESSFULL");
        response.setMessage("book price updation successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);


    }
}
