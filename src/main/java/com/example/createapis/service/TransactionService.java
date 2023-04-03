package com.example.createapis.service;

import com.example.createapis.Entity.Book;
import com.example.createapis.Entity.Borrower;
import com.example.createapis.Entity.Transaction;
import com.example.createapis.repository.BookRepository;
import com.example.createapis.repository.BorrowerRepository;
import com.example.createapis.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BorrowerRepository borrowerRepository;
    public ResponseEntity<Optional<Book>> borrowBook(long bookId, long borrowerId) {
        Optional<Book> book= bookRepository.findById(bookId);
        Optional<Borrower> borrower=borrowerRepository.findById(borrowerId);
        log.info("book responsee="+book.toString());
        log.info("borrower responsee="+borrower.toString());

        if(book.isPresent() && borrower.isPresent()){
            Transaction transaction=new Transaction();
            transaction.setBookId(bookId);
            transaction.setBorrowerId(borrowerId);
            transaction.setDate(LocalDate.now());
            transactionRepository.save(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity deleteBook(long bookId, long borrowerId) {
        Optional<Book> book= bookRepository.findById(bookId);
        Optional<Borrower> borrower=borrowerRepository.findById(borrowerId);
        log.info("book responsee="+book.toString());
        log.info("borrower responsee="+borrower.toString());
        if(book.isPresent() && borrower.isPresent()){
            Transaction transaction=transactionRepository.findByBookId(bookId);
            log.info("response from transaction repository "+transaction.toString());
            if(borrowerId==transaction.getBorrowerId()){
                transactionRepository.delete(transaction);
                return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
            }


        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data not found");

    }
}
