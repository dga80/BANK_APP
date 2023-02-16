package com.bank.bank.models.controller;

import com.bank.bank.models.Account;
import com.bank.bank.models.AccountHolder;
import com.bank.bank.models.DTO.AccountDTO;
import com.bank.bank.repositories.AccountHolderRepository;
import com.bank.bank.repositories.AccountRepository;
import com.bank.bank.services.AccountHolderService;
import com.bank.bank.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountHolderController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    AdminService adminService;

    @GetMapping("/getAccounts/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Account> findAllAccountsOfAccountHolder(@PathVariable Integer id){
      return accountHolderService.findAllAccounts(id);
    }
}
