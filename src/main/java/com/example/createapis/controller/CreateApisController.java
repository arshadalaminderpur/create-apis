package com.example.createapis.controller;


import com.example.createapis.Entity.Person;
import com.example.createapis.request.UserRequest;
import com.example.createapis.response.UserResponse;
import com.example.createapis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public UserResponse checkValidUser(@RequestBody Person request){
        return service.authenticateUser(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> addUser(@RequestBody Person person){
        return service.addUserIntoDB(person);

    }




}
