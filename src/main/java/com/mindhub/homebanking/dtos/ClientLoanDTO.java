package com.mindhub.homebanking.dtos;


import com.mindhub.homebanking.models.ClientLoan;

import javax.persistence.Entity;
import javax.persistence.Id;


public class ClientLoanDTO {

private long id;

private long LoanId;

private String name;

private double amount;

private Integer payments;

    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.LoanId = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
    }

    public long getId() {
        return id;
    }

    public long getLoanId() {
        return LoanId;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }
}
