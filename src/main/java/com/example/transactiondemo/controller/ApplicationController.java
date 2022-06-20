package com.example.transactiondemo.controller;

import com.example.transactiondemo.dto.request.*;
import com.example.transactiondemo.dto.response.*;
import com.example.transactiondemo.service.ApplicationAuthenticationManager;
import com.example.transactiondemo.service.SecurityUserDetailsService;
import com.example.transactiondemo.service.TransactionService;
import com.example.transactiondemo.service.UserService;
import com.example.transactiondemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ApplicationAuthenticationManager authenticationManager;

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/")
    public ResponseEntity<String> getHome() {
        return ResponseEntity.ok("welcome");
    }

    @PostMapping("/signup")
    public Response<SignUpResponse> signUp(@RequestBody SignUpRequest request) {

        userService.createAccount(request);
        return new Response<SignUpResponse>("00", "success", new SignUpResponse("new account created"));
    }

    @PostMapping("/login")
    public Response<JwtResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUserId(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(authenticationRequest.getUserId());
        final String accessToken = jwtUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(accessToken);

        return new Response<JwtResponse>("00", "authentication success", jwtResponse);
    }

    @PostMapping("/fund-transfer")
    public Response<FundTransferResponse> fundTransfer(@RequestBody FundTransferRequest request) {

        FundTransferResponse response = transactionService.fundTransfer(request);

        return new Response<>("00", "transfer success", response);
    }

    @PostMapping("/balance")
    public Response<BalanceResponse> balance(@RequestBody BalanceRequest request) {

        BalanceResponse response = transactionService.checkBalance(request);

        return new Response<>("00", "success", response);
    }

    @PostMapping("/transaction-history")
    public Response<TransactionHistoryResponse> transactionHistory(@RequestBody TransactionHistoryRequest request) {

        TransactionHistoryResponse response = transactionService.getTransactionHistory(request);

        return new Response<>("00", "success", response);
    }
}
