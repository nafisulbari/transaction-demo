package com.example.transactiondemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TransactionHistoryResponse {

    private List<TransactionResponse> transactionResponses;
}
