package com.bank.bank.models;


import com.bank.bank.enums.AccountStatus;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
@Entity
public class CheckingAccount extends Account{
    private BigDecimal monthlyMaintenanceFee = new BigDecimal("12.0");
    private BigDecimal minimumBalance = new BigDecimal("250.0");


    public CheckingAccount() {
    }

    public CheckingAccount(BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, AccountStatus status, BigDecimal monthlyMaintenanceFee, BigDecimal minimumBalance) {
        super(balance, secretKey, primaryOwner, secondaryOwner, penaltyFee, status);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
    }
    private void checkMinimumBalance() {
        BigDecimal currentBalance = getBalance();
        if (currentBalance.compareTo(minimumBalance) < 0) {
            BigDecimal newBalance = currentBalance.subtract(getPenaltyFee());
            setBalance(newBalance);
        }
    }
    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
