package com.example.createapis.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private long id;
    private long bookId;
    private long borrowerId;
    private LocalDate date;
}
