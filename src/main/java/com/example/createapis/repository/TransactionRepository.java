package com.example.createapis.repository;

import com.example.createapis.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Optional<Transaction> findByBookId(long bookId);

    Optional<Transaction> findByBorrowerId(long borrowerId);
}
