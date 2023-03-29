package com.example.createapis.service;

import com.example.createapis.Entity.Person;

import com.example.createapis.repository.UserRepository;
import com.example.createapis.response.UserResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;
    public UserResponse authenticateUser(Person request) {
        List<Person> users=repository.findByUserid(request.getUserid());
        UserResponse userResponse=new UserResponse();
        if(users.size()==0){
            userResponse.setToken("User doesn't exist");
            return userResponse;
        }
        log.info("users response from repository "+users.toString());
        userResponse.setToken("valid user");
        log.info("returning response from service "+userResponse.toString());
        return userResponse;


    }

    public ResponseEntity<UserResponse> addUserIntoDB(Person person) {
        log.info("person data "+person.toString());
        List<Person> user=null;
        UserResponse userResponse=new UserResponse();
        user= repository.findByUserid(person.getUserid());
        log.info("data returned from db "+user.toString()+" size="+user.size());
        if(user.size()!=0) {
            userResponse.setToken("Duplicate user");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(userResponse);

        }

            repository.save(person);

            userResponse.setToken("Signup successfully");
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);

    }

}
