package com.example.createapis.service;

import com.example.createapis.model.Person;

import com.example.createapis.repository.UserRepository;
import com.example.createapis.response.Response;
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
    public Response authenticateUser(Person request) {
        List<Person> users=repository.findByUserid(request.getUserid());
        Response userResponse=new Response();
        if(users.size()==0){
            userResponse.setStatus("User doesn't exist");
            return userResponse;
        }
        log.info("users response from repository "+users.toString());
        userResponse.setStatus("valid user");
        log.info("returning response from service "+userResponse.toString());
        return userResponse;


    }

    public ResponseEntity<Response> addUserIntoDB(Person person) {
        log.info("person data "+person.toString());
        List<Person> user=null;
        Response userResponse=new Response();
        user= repository.findByUserid(person.getUserid());
        log.info("data returned from db "+user.toString()+" size="+user.size());
        if(user.size()!=0) {
            userResponse.setStatus("Duplicate user");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(userResponse);

        }

            repository.save(person);

            userResponse.setStatus("Signup successfully");
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);

    }

}
