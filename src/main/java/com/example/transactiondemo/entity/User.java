package com.example.transactiondemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
@ToString
@Getter
@Setter
public class User {

    @EmbeddedId
    private UserId userId;

    private String password;
}


