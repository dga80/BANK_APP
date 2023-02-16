package com.bank.bank.services;

import com.bank.bank.models.Account;
import com.bank.bank.models.ThirdParty;
import com.bank.bank.repositories.AccountHolderRepository;
import com.bank.bank.repositories.AccountRepository;
import com.bank.bank.repositories.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class ThirdPartyService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    public void sendMoney(String hashedKey, String secretKey, Integer accountId, BigDecimal amount, Integer targetAccountId) {
        ThirdParty thirdParty = validateThirdParty(hashedKey, secretKey);

        Account sourceAccount = accountRepository.findByIdAndUser(targetAccountId, thirdParty);
        if (sourceAccount == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid source account");
        }
        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }

        Account targetAccount = accountRepository.findById(accountId).orElse(null);
        if (targetAccount == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid target account");
        }
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
        accountRepository.save(targetAccount);

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        accountRepository.save(sourceAccount);
    }

    public void receiveMoney(String hashedKey, String secretKey, Integer accountId, BigDecimal amount) {
        ThirdParty thirdParty = validateThirdParty(hashedKey, secretKey);

        Account targetAccount = accountRepository.findById(accountId).orElse(null);
        if (targetAccount == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid target account");
        }
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
        accountRepository.save(targetAccount);
    }

    private ThirdParty validateThirdParty(String hashedKey, String secretKey) {
        ThirdParty thirdParty = (ThirdParty) thirdPartyRepository.findThirdByHashedKey(hashedKey);
        if (thirdParty == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid third party credentials");
        }
        return thirdParty;
    }

}

