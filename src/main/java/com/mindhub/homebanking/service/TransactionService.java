package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TransactionService {

    public List<TransactionDTO> getTransactions();

    public TransactionDTO getTransactionDTO(Long id);


    public void saveTransaction(Transaction transaction);
}
