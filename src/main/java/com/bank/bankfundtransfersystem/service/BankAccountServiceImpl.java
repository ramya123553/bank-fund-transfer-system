package com.bank.bankfundtransfersystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bankfundtransfersystem.exception.ResourceNotFoundException;
import com.bank.bankfundtransfersystem.model.BankAccount;
import com.bank.bankfundtransfersystem.repository.BankAccountRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService{
	
	private final BankAccountRepository bankAccountRepository;
	
	@Autowired
	public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
		this.bankAccountRepository = bankAccountRepository;
	}

	@Override
	public BankAccount createBankAccount(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount) ;
	}

	@Override
	public List<BankAccount> getAllAccounts() {
		return bankAccountRepository.findAll();
	}

	@Override
	public Optional<BankAccount> getAccountById(Long id) {
		return Optional.ofNullable(bankAccountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bank account not found with ID: " + id)));
	}

	@Override
	public BankAccount updateBankAccount(Long id, BankAccount updatedAccount) {
		BankAccount existing = bankAccountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bank account not found with ID: " + id));
		
		existing.setAccountHolderName(updatedAccount.getAccountHolderName());
		existing.setBalance(updatedAccount.getBalance());
		existing.setAccountNumber(updatedAccount.getAccountNumber());
		return bankAccountRepository.save(existing);
	}

	@Override
	public void deleteBankAccount(Long id) {
		BankAccount existing = bankAccountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bank account not found with ID: " + id));
		bankAccountRepository.delete(existing);
		
	}

}
