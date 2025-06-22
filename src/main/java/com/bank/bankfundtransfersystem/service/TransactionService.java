package com.bank.bankfundtransfersystem.service;

import java.util.List;

import com.bank.bankfundtransfersystem.model.Transaction;

public interface TransactionService {
	
	Transaction createTransaction(Long senderId, Long receiverId, Double amount);
	
	List<Transaction> getAllTransactions();
	
	Transaction getTransactionById(Long id);
	
	String transferFunds(Long senderId, Long receiverId, double amount);

}
