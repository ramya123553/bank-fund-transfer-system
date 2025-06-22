package com.bank.bankfundtransfersystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bankfundtransfersystem.exception.ResourceNotFoundException;
import com.bank.bankfundtransfersystem.model.BankAccount;
import com.bank.bankfundtransfersystem.model.Transaction;
import com.bank.bankfundtransfersystem.repository.BankAccountRepository;
import com.bank.bankfundtransfersystem.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionRepository transactionRepository;
	private final BankAccountRepository bankAccountRepository;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository,
			BankAccountRepository bankAccountRepository) {
		this.transactionRepository = transactionRepository;
		this.bankAccountRepository = bankAccountRepository;
	}

	@Override
	public Transaction createTransaction(Long senderId, Long receiverId, Double amount) {
		BankAccount sender = bankAccountRepository.findById(senderId)
				.orElseThrow(() -> new ResourceNotFoundException("Sender account not found with ID: " + senderId));
		
		BankAccount receiver = bankAccountRepository.findById(receiverId)
				.orElseThrow(() -> new ResourceNotFoundException("Receiver account not found with ID: " + receiverId));
		
		if(sender.getBalance() < amount) {
			throw new IllegalArgumentException("Insufficient balance in sender's account");
		}
		
		sender.setBalance(sender.getBalance() - amount);
		receiver.setBalance(receiver.getBalance() + amount);
		
		bankAccountRepository.save(sender);
		bankAccountRepository.save(receiver);
		
		// Create transaction
        Transaction transaction = new Transaction();
        transaction.setReceiverAccountId(senderId);
        transaction.setReceiverAccountId(receiverId);
        transaction.setAmount(amount);
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setStatus("SUCCESS");

        return transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public Transaction getTransactionById(Long id) {
		return transactionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + id));
	}

	@Override
	public String transferFunds(Long senderId, Long receiverId, double amount) {
		BankAccount fromAccount = bankAccountRepository.findById(senderId)
				.orElseThrow(() -> new ResourceNotFoundException("From Account not found"));
		BankAccount toAccount = bankAccountRepository.findById(receiverId)
				.orElseThrow(() -> new ResourceNotFoundException("To Account not found"));
		
		if(fromAccount.getBalance() < amount) {
			return "Insuffient balance in source account.";
		}
		
		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);
		
		bankAccountRepository.save(fromAccount);
		bankAccountRepository.save(toAccount);
		
		Transaction transaction = new Transaction();
        transaction.setReceiverAccountId(senderId);
        transaction.setReceiverAccountId(receiverId);
        transaction.setAmount(amount);
        transaction.setTransactionTime(LocalDateTime.now());
        
        transactionRepository.save(transaction);
        
        return "Transfer successful.";
		
	}

}
