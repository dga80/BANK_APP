package com.bank.bank.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public abstract class Admin extends User{


    public Admin(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
    public Admin() {

    }



}
