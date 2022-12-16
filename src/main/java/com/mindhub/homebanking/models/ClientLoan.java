package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native" , name = "native")
    private long id;

    @ManyToOne
    @JoinColumn(name = "Cliente_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "Loan_id")
    private Loan loan;

    private double Amount;

    private Integer Payments;

    public ClientLoan() {
    }

    public ClientLoan(Client client, Loan loan, double amount, Integer payments) {
        this.client = client;
        this.loan = loan;
        this.Amount = amount;
        this.Payments = payments;
    }

    public long getId() {
        return id;
    }


    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public Integer getPayments() {
        return Payments;
    }

    public void setPayments(Integer payments) {
        Payments = payments;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
