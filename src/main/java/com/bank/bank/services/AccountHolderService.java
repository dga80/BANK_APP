package com.bank.bank.services;

import com.bank.bank.models.Account;
import com.bank.bank.models.AccountHolder;
import com.bank.bank.models.CheckingAccount;
import com.bank.bank.models.DTO.AccountDTO;
import com.bank.bank.models.DTO.TransactionDTO;
import com.bank.bank.models.Transaction;
import com.bank.bank.repositories.AccountHolderRepository;
import com.bank.bank.repositories.AccountRepository;
import com.bank.bank.repositories.TransactionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    public List<Account> findAllAccounts(Integer id) {
        AccountHolder primaryOwner = accountHolderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return primaryOwner.getAccounts();
    }



    public Transaction makeTransaction(TransactionDTO transactionDTO){

        Optional<Account> optionalOriginAccount = accountRepository.findById(transactionDTO.getOriginAccountID());
        Optional<Account> optionalEndAccount = accountRepository.findById(transactionDTO.getEndAccountID());

        if (optionalOriginAccount.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Origin account not found");
        }

        if (optionalEndAccount.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"End account not found");
        }

        Account originAccount = optionalOriginAccount.get();
        Account endAccount = optionalEndAccount.get();
        BigDecimal amount = new BigDecimal(transactionDTO.getQuantity());

        if (originAccount.getBalance().compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Origin account does not have sufficient funds");
        }

        originAccount.setBalance(originAccount.getBalance().subtract(amount));
        endAccount.setBalance(endAccount.getBalance().add(amount));


        Transaction transaction = new Transaction(accountRepository.save(originAccount), accountRepository.save(endAccount), amount);


        return transactionRepository.save(transaction);
    }

}
