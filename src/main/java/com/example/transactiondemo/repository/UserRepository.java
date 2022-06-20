package com.example.transactiondemo.repository;

import com.example.transactiondemo.entity.User;
import com.example.transactiondemo.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {

    Optional<User> findByUserIdUserId(String userId);
}
