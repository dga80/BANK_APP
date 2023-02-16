package com.bank.bank.models.DTO;

public class ThirdPartyDTO {
    private String hashedKey;
    private String secretKey;
    private String amount;

    public ThirdPartyDTO(String hashedKey, String secretKey, String amount) {
        this.hashedKey = hashedKey;
        this.secretKey = secretKey;
        this.amount = amount;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
