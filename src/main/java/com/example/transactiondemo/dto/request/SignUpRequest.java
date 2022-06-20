package com.example.transactiondemo.dto.request;

import lombok.Data;

@Data
public class SignUpRequest {

    private String userId;

    private int accountNo;

    private String password;
}
