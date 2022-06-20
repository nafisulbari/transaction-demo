package com.example.transactiondemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {

    private double amount;

    private int referenceNumber;

    private String transactionFlag;
}
