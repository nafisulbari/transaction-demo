package com.example.transactiondemo.service;

import com.example.transactiondemo.dto.request.BalanceRequest;
import com.example.transactiondemo.dto.request.FundTransferRequest;
import com.example.transactiondemo.dto.request.TransactionHistoryRequest;
import com.example.transactiondemo.dto.response.BalanceResponse;
import com.example.transactiondemo.dto.response.FundTransferResponse;
import com.example.transactiondemo.dto.response.TransactionHistoryResponse;
import com.example.transactiondemo.dto.response.TransactionResponse;
import com.example.transactiondemo.entity.AccountDetails;
import com.example.transactiondemo.entity.TransactionDetails;
import com.example.transactiondemo.entity.TransactionDetailsId;
import com.example.transactiondemo.repository.AccountDetailsRepository;
import com.example.transactiondemo.repository.TransactionDetailsRepository;
import com.example.transactiondemo.repository.UserRepository;
import com.example.transactiondemo.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    public FundTransferResponse fundTransfer(FundTransferRequest request) {
        AccountDetails debitAccount = accountDetailsRepository.findByAccountNo(request.getFromAccount());
        debitAccount.setAvailableBalance(debitAccount.getAvailableBalance() - request.getAmount());
        accountDetailsRepository.save(debitAccount);

        AccountDetails creditAccount = accountDetailsRepository.findByAccountNo(request.getToAccount());
        creditAccount.setAvailableBalance(creditAccount.getAvailableBalance() + request.getAmount());
        accountDetailsRepository.save(creditAccount);

        int debitReferenceNumber = processTransaction(request);

        FundTransferResponse response = new FundTransferResponse();
        response.setAccountBalance(debitAccount.getAvailableBalance());
        response.setReferenceNumber(debitReferenceNumber);

        return response;
    }

    private int processTransaction(FundTransferRequest request) {

        int referenceNumber = RandomUtil.generateRandom();

        TransactionDetailsId debitTransactionId = new TransactionDetailsId();
        debitTransactionId.setTransactionFlag("D");
        debitTransactionId.setReferenceNumber(referenceNumber);
        debitTransactionId.setAccountNo(request.getFromAccount());

        TransactionDetails debitTransaction = new TransactionDetails();
        debitTransaction.setTransactionDetailsId(debitTransactionId);
        debitTransaction.setTransactionAmount(request.getAmount());

        transactionDetailsRepository.save(debitTransaction);

        TransactionDetailsId creditTransactionId = new TransactionDetailsId();
        creditTransactionId.setTransactionFlag("C");
        creditTransactionId.setReferenceNumber(referenceNumber);
        creditTransactionId.setAccountNo(request.getToAccount());

        TransactionDetails creditTransaction = new TransactionDetails();
        creditTransaction.setTransactionDetailsId(creditTransactionId);
        creditTransaction.setTransactionAmount(request.getAmount());

        transactionDetailsRepository.save(creditTransaction);

        return debitTransactionId.getReferenceNumber();
    }

    public BalanceResponse checkBalance(BalanceRequest request) {

        double balance = accountDetailsRepository.getBalanceByAccountNo(request.getAccountNo());

        return new BalanceResponse(balance);
    }

    public TransactionHistoryResponse getTransactionHistory(TransactionHistoryRequest request) {

         List<TransactionDetails> transactionDetails =
                 transactionDetailsRepository.getTransactionDetailsByTransactionDetailsId_AccountNo(request.getAccountNo());

         List<TransactionResponse> transactionList = new ArrayList<>();
         transactionDetails.forEach(td ->{
             transactionList.add(new TransactionResponse(td.getTransactionAmount(),
                     td.getTransactionDetailsId().getReferenceNumber(),
                     td.getTransactionDetailsId().getTransactionFlag()));
         });
         return new TransactionHistoryResponse(transactionList);
    }
}
