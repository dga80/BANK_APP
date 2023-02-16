package com.bank.bank.models;


import com.bank.bank.enums.AccountStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;

import java.math.BigDecimal;
@Entity
public class StudentChecking extends Account{
    public StudentChecking() {
    }

    public StudentChecking(BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, AccountStatus status) {
        super(balance, secretKey, primaryOwner, secondaryOwner, penaltyFee, status);
        setPenaltyFee(BigDecimal.valueOf(0));
    }

}
