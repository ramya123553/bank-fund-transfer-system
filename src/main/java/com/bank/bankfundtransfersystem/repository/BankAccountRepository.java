package com.bank.bankfundtransfersystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.bankfundtransfersystem.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    // Custom query methods (if needed later)
    BankAccount findByAccountNumber(String accountNumber);
}
