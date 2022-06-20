package com.example.transactiondemo.repository;

import com.example.transactiondemo.entity.TransactionDetails;
import com.example.transactiondemo.entity.TransactionDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, TransactionDetailsId> {

    List<TransactionDetails> getTransactionDetailsByTransactionDetailsId_AccountNo(int accountNo);
}
