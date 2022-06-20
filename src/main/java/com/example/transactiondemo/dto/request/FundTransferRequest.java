package com.example.transactiondemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FundTransferRequest {

    private double amount;

    private int fromAccount;

    private int toAccount;
}
