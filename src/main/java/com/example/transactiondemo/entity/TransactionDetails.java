package com.example.transactiondemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class TransactionDetails {

    @EmbeddedId
    private TransactionDetailsId transactionDetailsId;

    private double transactionAmount;
}
