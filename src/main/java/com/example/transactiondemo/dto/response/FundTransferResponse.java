package com.example.transactiondemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundTransferResponse {

    private double accountBalance;

    private int referenceNumber;
}
