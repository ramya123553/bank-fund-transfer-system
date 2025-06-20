package com.bank.bankfundtransfersystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return bankAccountRepository.findById(id);
	}

	@Override
	public BankAccount updateBankAccount(Long id, BankAccount updatedAccount) {
		return bankAccountRepository.findById(id)
				.map(existing -> {
					existing.setAccountHolderName(updatedAccount.getAccountHolderName());
					existing.setBalance(updatedAccount.getBalance());
					return bankAccountRepository.save(existing);
					
				})
				.orElseThrow(() -> new RuntimeException("BankAccount not found with id: " + id));
	}

	@Override
	public void deleteBankAccount(Long id) {
		bankAccountRepository.deleteById(id);
		
	}

}
