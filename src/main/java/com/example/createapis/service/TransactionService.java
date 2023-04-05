package com.example.createapis.service;

import com.example.createapis.model.Book;
import com.example.createapis.model.Borrower;
import com.example.createapis.model.Transaction;
import com.example.createapis.repository.BookRepository;
import com.example.createapis.repository.BorrowerRepository;
import com.example.createapis.repository.TransactionRepository;
import com.example.createapis.response.Response;
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
    public ResponseEntity<Response> borrowBook(long bookId, long borrowerId) {
        Optional<Book> bookOptional= bookRepository.findById(bookId);
        Optional<Borrower> borrowerOptional=borrowerRepository.findById(borrowerId);
        log.info("book responsee="+bookOptional.toString());
        log.info("borrower responsee="+borrowerOptional.toString());
        Response response=new Response();

        if(bookOptional.isPresent() && borrowerOptional.isPresent() && bookOptional.get().getNoCopies()>0){
            Transaction transaction=new Transaction();
            transaction.setBookId(bookId);
            transaction.setBorrowerId(borrowerId);
            transaction.setBookedDate(LocalDate.now());
            transactionRepository.save(transaction);
            Book book=bookOptional.get();
            book.setNoCopies(book.getNoCopies()-1);
            bookRepository.save(book);

            response.setStatus("SUCCESSFULL");
            response.setMessage("Book added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else if (bookOptional.isPresent() && bookOptional.get().getNoCopies()<1) {
            response.setStatus("Book not available");
            response.setMessage("please give a book is ehich is available");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setStatus("book id or borrower id is not valid");
        response.setMessage("please enter a valid book id and borrower id");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity deleteBook(long bookId, long borrowerId) {
        Optional<Book> book= bookRepository.findById(bookId);
        Optional<Borrower> borrower=borrowerRepository.findById(borrowerId);
        log.info("book responsee="+book.toString());
        log.info("borrower responsee="+borrower.toString());
        if(book.isPresent() && borrower.isPresent()){
            Optional<Transaction> transactionOptional=transactionRepository.findByBookId(bookId);
            Transaction transaction=transactionOptional.get();
            log.info("response from transaction repository "+transaction.toString());
            if(borrowerId==transaction.getBorrowerId()){
                transactionRepository.delete(transaction);
                return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
            }


        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data not found");

    }

    public ResponseEntity<Response> returnBook(long bookId, long borrowerId) {
        Optional<Transaction> transactionOptionalBook= transactionRepository.findByBookId(bookId);
        Optional<Transaction> transactionOptionalBorrower=transactionRepository.findByBorrowerId(borrowerId);
        Response response=new Response();
        if(transactionOptionalBook.isPresent() && transactionOptionalBorrower.isPresent()){
            Transaction transactionBook=transactionOptionalBook.get();
            Transaction transactionBorrower=transactionOptionalBorrower.get();
            if(transactionBook.getBorrowerId()==transactionBorrower.getBorrowerId() && transactionBook.getBookId()==transactionBorrower.getBookId()){
                transactionBook.setReturnedDate(LocalDate.now());
                transactionRepository.save(transactionBook);
                Optional<Book> bookOptional=bookRepository.findById(bookId);
                Book book=bookOptional.get();
                book.setNoCopies(book.getNoCopies()+1);
                bookRepository.save(book);
                response.setStatus("Succefull");
                response.setMessage("Book return successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);

            }
            response.setStatus("Book id and borrower id is not matching");
            response.setMessage("please enter a valid book id and borrower id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }
        response.setStatus("Book id and borrower id is not Present");
        response.setMessage("please enter a valid book id and borrower id");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
}
