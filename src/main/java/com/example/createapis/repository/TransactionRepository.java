package com.example.createapis.repository;

import com.example.createapis.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Transaction findByBookId(long bookId);
}
