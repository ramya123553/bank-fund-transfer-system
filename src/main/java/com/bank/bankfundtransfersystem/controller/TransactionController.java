package com.bank.bankfundtransfersystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankfundtransfersystem.model.Transaction;
import com.bank.bankfundtransfersystem.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	private final TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping("/transfer")
	public Transaction createTransaction(
			@RequestParam Long senderId,
			@RequestParam Long receiverId,
			@RequestParam Double amount) {
		return transactionService.createTransaction(senderId, receiverId, amount);
	}
	
	@GetMapping("/all")
	public List<Transaction> getAllTransactions() {
		return transactionService.getAllTransactions();
	}
	
	@GetMapping("/get")
	public Transaction getTransactionById(@RequestParam (name = "id") Long id) {
		return transactionService.getTransactionById(id);
	}

}
