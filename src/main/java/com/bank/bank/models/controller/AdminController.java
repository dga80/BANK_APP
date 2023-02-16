package com.bank.bank.models.controller;

import com.bank.bank.models.Account;
import com.bank.bank.models.DTO.AccountDTO;
import com.bank.bank.repositories.AccountHolderRepository;
import com.bank.bank.repositories.AccountRepository;
import com.bank.bank.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
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
    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("id") Integer accountId) {
        accountRepository.deleteById(accountId);
    }

}
