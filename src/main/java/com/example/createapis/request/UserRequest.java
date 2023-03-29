package com.example.createapis.request;

import lombok.Data;

@Data
public class UserRequest {

    private String userid;
    private String password;



    public UserRequest(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }
}
