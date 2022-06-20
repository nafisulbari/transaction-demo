package com.example.transactiondemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UserId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private int accountNo;
}