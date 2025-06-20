package com.bank.bankfundtransfersystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankfundtransfersystem.model.BankAccount;
import com.bank.bankfundtransfersystem.service.BankAccountService;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
	
	private final BankAccountService bankAccountService;
	
	@Autowired
	public BankAccountController(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	
	@PostMapping("/create")
	public BankAccount createAccount(@RequestBody BankAccount bankAccount) {
		return bankAccountService.createBankAccount(bankAccount);
	}
	
	@GetMapping("/all")
	public List<BankAccount> getAllAccounts(){
		return bankAccountService.getAllAccounts();
	}
	
	@PutMapping("/update")
	public BankAccount updateAccount(@RequestParam (name = "id") Long id, @RequestBody BankAccount bankAccount) {
		return bankAccountService.updateBankAccount(id, bankAccount);
	}
	
	@DeleteMapping("/delete")
	public String deleteAccount(@RequestParam (name = "id") Long id) {
		bankAccountService.deleteBankAccount(id);
		return "Bank account with ID " + id + " deleted successfully.";
		
	}

}
