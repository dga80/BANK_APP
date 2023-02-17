package com.bank.bank.models;

import com.bank.bank.models.DTO.AccountDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolder extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="streetName",column=@Column(name="mailing_name")),
            @AttributeOverride(name="city",column=@Column(name="mailing_city")),
            @AttributeOverride(name="state",column=@Column(name="mailing_state")),
            @AttributeOverride(name="zip",column=@Column(name="mailing_zip"))

    })
    private Address mailingAddress;

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> accounts= new ArrayList<>();


    public AccountHolder() {
    }
    public AccountHolder(AccountDTO accountDTO) {
    }

    public AccountHolder(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    public AccountHolder(String name, LocalDate birthDate, Address primaryAddress, Address mailingAddress) {
        super(name, birthDate);
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
