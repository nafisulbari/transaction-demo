package com.example.transactiondemo.dto.request;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String userId;
    private String password;
}
