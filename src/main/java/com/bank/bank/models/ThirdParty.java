package com.bank.bank.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ThirdParty extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(String name, LocalDate birthDate, Integer id, String hashedKey) {
        super(name, birthDate);
        this.id = id;
        this.hashedKey = hashedKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

}
