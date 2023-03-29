package com.example.createapis.controller;

import com.example.createapis.Entity.Book;
import com.example.createapis.Entity.Borrower;
import com.example.createapis.service.BookService;
import com.example.createapis.service.BorrowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BorrowerController {

    @Autowired
    private BorrowerService service;
    @PostMapping("/add-borrower")
    public ResponseEntity<Borrower> addBorrower(@RequestBody Borrower borrower){
        log.info("request body in controller "+borrower.toString());
        return service.addBorrower(borrower);

    }

    @RequestMapping(value = "/delete-borrower{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteBorrower(@PathVariable long id){
        return service.deleteBorrower(id);
    }

    @RequestMapping(value = "/update-borrower{id}",method = RequestMethod.PUT)
    public ResponseEntity updateBorrower(@PathVariable long id,@RequestBody Borrower borrower){
        return service.updateBorrower(id,borrower);
    }


}
