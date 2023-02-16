package com.bank.bank.models.DTO;

public class TransactionDTO {
    private Integer originAccountID;
    private Integer endAccountID;
    private String quantity;

    public TransactionDTO(Integer originAccountID, Integer endAccountID, String quantity) {
        this.originAccountID = originAccountID;
        this.endAccountID = endAccountID;
        this.quantity = quantity;
    }

    public Integer getOriginAccountID() {
        return originAccountID;
    }

    public void setOriginAccountID(Integer originAccountID) {
        this.originAccountID = originAccountID;
    }

    public Integer getEndAccountID() {
        return endAccountID;
    }

    public void setEndAccountID(Integer endAccountID) {
        this.endAccountID = endAccountID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
