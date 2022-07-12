package com.example.transactiondemo.repository;

import com.example.transactiondemo.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Integer> {

    Optional<AccountDetails> findByAccountNo(int accountNo);

    @Query("select a.availableBalance " +
            "from AccountDetails a " +
            "where a.accountNo =:accountNo")
    double getBalanceByAccountNo(@Param("accountNo") int accountNo);
}
