package com.example.createapis.service;

import com.example.createapis.Entity.Book;
import com.example.createapis.Entity.Borrower;
import com.example.createapis.repository.BookRepository;
import com.example.createapis.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowerService {

    @Autowired
    private BorrowerRepository repository;
    public ResponseEntity<Borrower> addBorrower(Borrower borrower) {
        repository.save(borrower);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrower);
    }

    public ResponseEntity deleteBorrower(long id) {
        Optional<Borrower> book=repository.findById(id);
        if(book.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("content not found");
    }

    public ResponseEntity updateBorrower(long id, Borrower borrower) {

        Optional<Borrower> bookDb=repository.findById(id);
        if(bookDb.isPresent()){
            repository.deleteById(id);
            borrower.setId(id);
            repository.save(borrower);
            return ResponseEntity.status(HttpStatus.OK).body("updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("content not found");


    }
}
