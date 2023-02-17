package com.bank.bank.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class TransactionThirdParty{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer accountID;
    private Integer thirdPartyID;
    private BigDecimal quantity;

    public TransactionThirdParty() {
    }

    public TransactionThirdParty(Integer accountID, Integer thirdPartyID, BigDecimal quantity) {
        this.accountID = accountID;
        this.thirdPartyID = thirdPartyID;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public Integer getThirdPartyID() {
        return thirdPartyID;
    }

    public void setThirdPartyID(Integer thirdPartyID) {
        this.thirdPartyID = thirdPartyID;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }


}
