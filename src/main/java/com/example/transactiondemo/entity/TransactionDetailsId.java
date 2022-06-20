package com.example.transactiondemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class TransactionDetailsId implements Serializable {

    private static final long serialVersionUID = 1L;

    private int accountNo;

    private String transactionFlag;

    private int referenceNumber;
}
