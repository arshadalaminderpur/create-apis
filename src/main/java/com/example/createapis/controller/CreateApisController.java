package com.example.createapis.controller;


import com.example.createapis.model.Person;
import com.example.createapis.response.Response;
import com.example.createapis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CreateApisController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public Response checkValidUser(@RequestBody Person request){
        return service.authenticateUser(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> addUser(@RequestBody Person person){
        return service.addUserIntoDB(person);

    }




}
