package com.example.transactiondemo.service;

import com.example.transactiondemo.dto.request.SignUpRequest;
import com.example.transactiondemo.entity.User;
import com.example.transactiondemo.entity.UserId;
import com.example.transactiondemo.exception.DuplicateEntityException;
import com.example.transactiondemo.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createAccount(SignUpRequest request) {

        Optional<User> optionalUser = userRepository.findByUserIdUserId(request.getUserId());
        if (optionalUser.isPresent()) {
            log.info("Duplicate user creation, user already exists with id: " + request.getUserId());
            throw new DuplicateEntityException("user already exists");
        }

        UserId userId = new UserId();
        userId.setUserId(request.getUserId());
        userId.setAccountNo(request.getAccountNo());

        User user = new User();
        user.setUserId(userId);
        user.setPassword(request.getPassword());

        userRepository.save(user);
    }
}
