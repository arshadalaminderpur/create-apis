package com.example.createapis.controller;

import com.example.createapis.response.Response;
import com.example.createapis.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
public class TransactionController {

    @Autowired
    TransactionService service;

    @PostMapping("/borrow-book/{bookId}/{borrowerId}")
    public ResponseEntity<Response> borrowBook(@PathVariable long bookId, @PathVariable long borrowerId){
        log.info("book id="+bookId+" borrowerId="+borrowerId);
        return service.borrowBook(bookId,borrowerId);

    }
    @PostMapping("/return-book/{bookId}/{borrowerId}")
    public ResponseEntity<Response> returnBook(@PathVariable long bookId, @PathVariable long borrowerId){
        log.info("book id="+bookId+" borrowerId="+borrowerId);
        return service.returnBook(bookId,borrowerId);

    }
    @RequestMapping(value = "/delete-book/{bookId}/{borrowerId}",method = RequestMethod.DELETE)
    public ResponseEntity deleteBook(@PathVariable("bookId") long bookId,
                                     @PathVariable("borrowerId") long borrowerId){
        return service.deleteBook(bookId,borrowerId);
    }




   @GetMapping("/code")
    public int codeKaroMat(){

        int []arr={-1,-8,0,5,-9};
        func(1,arr);
        log.info("arshad feature2 branch");
        Integer.toString(1);
        Arrays.sort(arr);

        return 0;


   }

    private void func(int time,int [] arr) {









    }

}
