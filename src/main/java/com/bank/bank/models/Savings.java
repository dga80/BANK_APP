package com.bank.bank.models;

import com.bank.bank.enums.AccountStatus;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Savings extends Account{
    private BigDecimal interestRate;
    private BigDecimal minimumBalance;
    private LocalDate lastInterestAddedDate;


    public Savings() {
    }

    public Savings(BigDecimal interestRate, BigDecimal minimumBalance) {
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
        this.lastInterestAddedDate = LocalDate.now();
    }

    public Savings(BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, AccountStatus status, BigDecimal interestRate, BigDecimal minimumBalance) {
        super(balance, secretKey, primaryOwner, secondaryOwner, penaltyFee, status);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
        this.lastInterestAddedDate = LocalDate.now();
    }
    @Override
    public BigDecimal getBalance() {
        BigDecimal balance = super.getBalance();
        LocalDate today = LocalDate.now();
        long yearsSinceLastInterest = ChronoUnit.YEARS.between(lastInterestAddedDate, today);
        if (yearsSinceLastInterest >= 1) {
            BigDecimal interest = balance.multiply(interestRate);
            balance = balance.add(interest);
            lastInterestAddedDate = today;
        }
        return balance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

}
