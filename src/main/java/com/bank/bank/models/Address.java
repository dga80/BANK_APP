package com.bank.bank.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String streetName;
    private String city;
    private String state;
    private String zip;

    public Address() {
    }

    public Address(String name, String city, String state, String zip) {
        this.streetName = name;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}

