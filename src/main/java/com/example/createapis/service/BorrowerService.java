package com.example.createapis.service;

import com.example.createapis.model.Borrower;
import com.example.createapis.repository.BorrowerRepository;
import com.example.createapis.response.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Transactional
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

    public ResponseEntity<Response> updateBorrower(long id, Borrower borrowerRequest) {

        Optional<Borrower> borrowerOptional=repository.findById(id);
        Response response=new Response();
        if(!borrowerOptional.isPresent()) {
            response.setStatus("invalid borrower id");
            response.setMessage("please enter valid borrower id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Borrower borrower=borrowerOptional.get();
        borrower.setPhone(borrowerRequest.getPhone());
        borrower.setName(borrowerRequest.getName());
        repository.save(borrower);
        response.setStatus("SUCCESSFULL");
        response.setMessage("Updation successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);





    }
}
