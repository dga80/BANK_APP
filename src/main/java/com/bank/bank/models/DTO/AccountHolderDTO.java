package com.bank.bank.models.DTO;

import com.bank.bank.models.Address;

import java.time.LocalDate;

public class AccountHolderDTO {
    private String NewName;
    private LocalDate NewDateOfBirth;
    private Address NewPrimaryAddress;
    private Address NewMailingAddress;

    public AccountHolderDTO(String newName, LocalDate newDateOfBirth, Address newPrimaryAddress, Address newMailingAddress) {
        NewName = newName;
        NewDateOfBirth = newDateOfBirth;
        NewPrimaryAddress = newPrimaryAddress;
        NewMailingAddress = newMailingAddress;
    }

    public String getNewName() {
        return NewName;
    }

    public void setNewName(String newName) {
        NewName = newName;
    }

    public LocalDate getNewDateOfBirth() {
        return NewDateOfBirth;
    }

    public void setNewDateOfBirth(LocalDate newDateOfBirth) {
        NewDateOfBirth = newDateOfBirth;
    }

    public Address getNewPrimaryAddress() {
        return NewPrimaryAddress;
    }

    public void setNewPrimaryAddress(Address newPrimaryAddress) {
        NewPrimaryAddress = newPrimaryAddress;
    }

    public Address getNewMailingAddress() {
        return NewMailingAddress;
    }

    public void setNewMailingAddress(Address newMailingAddress) {
        NewMailingAddress = newMailingAddress;
    }
}
