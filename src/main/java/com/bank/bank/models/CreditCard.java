package com.bank.bank.models;



import com.bank.bank.enums.AccountStatus;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class CreditCard extends Account{
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private LocalDate lastInterestAddedDate;

    public CreditCard() {
    }

    public CreditCard(BigDecimal creditLimit, BigDecimal interestRate) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public CreditCard(BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, AccountStatus status, BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, penaltyFee, status);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.lastInterestAddedDate = LocalDate.now();

    }
    @Override
    public void setBalance(BigDecimal balance) {
        super.setBalance(balance);

        LocalDate today = LocalDate.now();
        long monthsSinceLastInterest = ChronoUnit.MONTHS.between(lastInterestAddedDate, today);
        if (monthsSinceLastInterest >= 1) {
            BigDecimal interest = balance.multiply(interestRate);
            super.setBalance(balance.add(interest));
            lastInterestAddedDate = today;
        }
    }


    public LocalDate getLastInterestAddedDate() {
        return lastInterestAddedDate;
    }

    public void setLastInterestAddedDate(LocalDate lastInterestAddedDate) {
        this.lastInterestAddedDate = lastInterestAddedDate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
