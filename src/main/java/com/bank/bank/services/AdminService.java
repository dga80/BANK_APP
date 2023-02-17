package com.bank.bank.services;

import com.bank.bank.enums.AccountStatus;
import com.bank.bank.models.*;
import com.bank.bank.models.DTO.AccountDTO;
import com.bank.bank.models.DTO.AccountHolderDTO;
import com.bank.bank.models.DTO.ThirdPartyDTO;
import com.bank.bank.repositories.AccountHolderRepository;
import com.bank.bank.repositories.AccountRepository;
import com.bank.bank.repositories.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class AdminService {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public AccountHolder createAccountHolder(AccountDTO accountDTO) {

        AccountHolder newAccountHolder = new AccountHolder(accountDTO);
        return accountHolderRepository.save(newAccountHolder);
    }


    public Savings createSavings(AccountDTO accountDTO){
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Primary owner not found"));

        AccountHolder secondaryOwner = null;
        if(accountDTO.getSecondaryOwnerId() != null) {
            secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Secondary owner not found"));
        }

        String interestRate = accountDTO.getInterestRate();
        interestRate = (interestRate != null && interestRate.compareTo("0.5") <= 0) ? interestRate : "0.0025";

        String minimumBalance = accountDTO.getMinimumBalance();
        minimumBalance = (minimumBalance != null && minimumBalance.compareTo("100") <=0) ? minimumBalance : "1000";

        Savings savings = new Savings(new BigDecimal(accountDTO.getBalance()), accountDTO.getSecretKey(), primaryOwner, secondaryOwner, new BigDecimal(accountDTO.getPenaltyFee()), AccountStatus.ACTIVE, new BigDecimal(accountDTO.getInterestRate()),new BigDecimal(accountDTO.getMinimumBalance()));

        return accountRepository.save(savings);
    }
    public CreditCard createCreditCard(AccountDTO accountDTO){
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AccountHolder secondaryOwner = null;
        if(accountDTO.getSecondaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()) secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();

        String defaultCreditLimit = "100";
        String maxCreditLimit = "100000";
        String defaultInterestRate = "0.2";
        String minInterestRate = "0.1";

        String creditLimitOK = accountDTO.getCreditLimit() != null && accountDTO.getCreditLimit().compareTo(maxCreditLimit) <= 0 ? accountDTO.getCreditLimit() : defaultCreditLimit;

        String interestRateOK = accountDTO.getInterestRate() != null && accountDTO.getInterestRate().compareTo(minInterestRate) >= 0 ? accountDTO.getInterestRate() : defaultInterestRate;

        CreditCard creditCard = new CreditCard(new BigDecimal(accountDTO.getBalance()), accountDTO.getSecretKey(), primaryOwner, secondaryOwner, new BigDecimal(accountDTO.getPenaltyFee()), AccountStatus.ACTIVE, new BigDecimal(accountDTO.getCreditLimit()), new BigDecimal(accountDTO.getInterestRate()));
        return  accountRepository.save(creditCard);
    }

    public Account createCheckingOrStudentAccount(AccountDTO accountDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AccountHolder secondaryOwner = null;
        if (accountDTO.getSecondaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent())
            secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();

        if (primaryOwner.getAge() > 24) {
            CheckingAccount checkingAccount = new CheckingAccount(new BigDecimal(accountDTO.getBalance()), accountDTO.getSecretKey(), primaryOwner, secondaryOwner, new BigDecimal(accountDTO.getPenaltyFee()), AccountStatus.ACTIVE, new BigDecimal(accountDTO.getMonthlyMaintenanceFee()),new BigDecimal(accountDTO.getMinimumBalance()));
            return accountRepository.save(checkingAccount);
        } else {
            StudentChecking studentChecking = new StudentChecking(new BigDecimal(accountDTO.getBalance()), accountDTO.getSecretKey(), primaryOwner, secondaryOwner, new BigDecimal(accountDTO.getPenaltyFee()), AccountStatus.ACTIVE);
            return accountRepository.save(studentChecking);
        }
    }

    public Account updateBalance(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
        try {
            account.setBalance(new BigDecimal(accountDTO.getBalance()));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid balance value");
        }
        accountRepository.save(account);
        return account;
    }

    public ThirdParty addThirdParty(ThirdPartyDTO thirdPartyDTO) {
        if (thirdPartyRepository.findByHashedKey(thirdPartyDTO.getHashedKey()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ThirdParty with this hashedKey already exists");
        }
        ThirdParty newThirdParty = new ThirdParty();
        return thirdPartyRepository.save(newThirdParty);
    }
    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder = new AccountHolder(accountHolderDTO.getNewName(), accountHolderDTO.getNewDateOfBirth(), accountHolderDTO.getNewPrimaryAddress(), accountHolderDTO.getNewMailingAddress());
        return accountHolderRepository.save(accountHolder);
    }






}
