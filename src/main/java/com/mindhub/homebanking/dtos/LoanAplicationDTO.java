package com.mindhub.homebanking.dtos;

public class LoanAplicationDTO {

    private long id;
    private Double amount;
    private Integer payment;
    private String accountDestinit;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public String getAccountDestinit() {
        return accountDestinit;
    }


}