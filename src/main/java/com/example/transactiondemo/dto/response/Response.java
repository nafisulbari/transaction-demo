package com.example.transactiondemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {

    private String responseCode;

    private String responseDescription;

    private T data;
}
