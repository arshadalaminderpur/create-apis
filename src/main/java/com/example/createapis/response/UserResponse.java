package com.example.createapis.response;

import lombok.Data;

@Data
public class UserResponse {

    private String token;
    private String exception;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
