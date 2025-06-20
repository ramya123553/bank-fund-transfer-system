package com.bank.bankfundtransfersystem.service;

import java.util.List;
import java.util.Optional;

import com.bank.bankfundtransfersystem.model.BankAccount;

public interface BankAccountService {

	BankAccount createBankAccount(BankAccount bankAccount);
	
	List<BankAccount> getAllAccounts();
	
	Optional<BankAccount> getAccountById(Long id);
	
	BankAccount updateBankAccount(Long id, BankAccount updateAccount);
	
	void deleteBankAccount(Long id);
}
