package com.example.createapis.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Borrower {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String phone;
}
