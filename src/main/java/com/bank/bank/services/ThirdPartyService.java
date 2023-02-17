package com.bank.bank.services;

import com.bank.bank.models.Account;
import com.bank.bank.models.DTO.AccountDTO;
import com.bank.bank.models.DTO.ThirdPartyDTO;
import com.bank.bank.models.ThirdParty;
import com.bank.bank.models.TransactionThirdParty;
import com.bank.bank.repositories.*;
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
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ThirdTransRepository thirdTransRepository;


    public  TransactionThirdParty transferThirdParty(ThirdPartyDTO thirdPartyDTO) {

        ThirdParty thirdParty = thirdPartyRepository.findByHashedKey(thirdPartyDTO.getHashedKey())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "The third party does not exist"));

        Account account = accountRepository.findBySecretKey(thirdPartyDTO.getSecretKey())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        BigDecimal amountInBD = new BigDecimal(thirdPartyDTO.getAmount());
        account.setBalance(account.getBalance().add(amountInBD));
        accountRepository.save(account);
        TransactionThirdParty transactionThirdParty= new TransactionThirdParty(account.getId(),thirdParty.getId(),amountInBD);
        return thirdTransRepository.save(transactionThirdParty);
    }

}

