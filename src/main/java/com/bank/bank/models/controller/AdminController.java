package com.bank.bank.models.controller;

import com.bank.bank.models.Account;
import com.bank.bank.models.DTO.AccountDTO;
import com.bank.bank.models.DTO.TransactionDTO;
import com.bank.bank.models.Transaction;
import com.bank.bank.repositories.AccountHolderRepository;
import com.bank.bank.repositories.AccountRepository;
import com.bank.bank.services.AccountHolderService;
import com.bank.bank.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    AdminService adminService;

    @PostMapping("/new-savingsAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account findAllSavings(@RequestBody AccountDTO accountDTO){
        return adminService.createSavings(accountDTO);
    }
    @PostMapping("/new-checkingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account findAllChecking(@RequestBody AccountDTO accountDTO){
        return adminService.createCheckingOrStudentAccount(accountDTO);
    }
    @PostMapping("/new-creditAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account findAllCredits(@RequestBody AccountDTO accountDTO){
        return adminService.createCreditCard(accountDTO);
    }
    @PatchMapping("/new-balanceAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account updateBalance(@RequestBody AccountDTO accountDTO){
        return adminService.updateBalance(accountDTO);
    }
    @PatchMapping("/new-transference")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction makeTransaction(@RequestBody TransactionDTO transactionDTO){
        return accountHolderService.makeTransaction(transactionDTO);
    }



    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("id") Integer accountId) {
        accountRepository.deleteById(accountId);
    }

}
