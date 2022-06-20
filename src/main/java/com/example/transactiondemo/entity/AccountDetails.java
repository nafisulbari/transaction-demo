package com.example.transactiondemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class AccountDetails {

    @Id
    private int accountNo;

    private double availableBalance;
}
