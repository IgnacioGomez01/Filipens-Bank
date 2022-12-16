package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;


    @RequestMapping("/api/transactions")
    private List<TransactionDTO> getTransactions() {
        return transactionService.getTransactions();

    }

    @RequestMapping("/api/transactions/{id}")
    public TransactionDTO getTransactions(@PathVariable Long id) {
        return transactionService.getTransactionDTO(id);
    }

    @Transactional
    @PostMapping("/api/transactions/create")
    public ResponseEntity<Object> createTransaction(Authentication authentication, @RequestParam Double amount,@RequestParam String description, @RequestParam String originAccountNumber, @RequestParam String destinationAccountNumber) {

        Client authenticatedClient = clientService.findByEmail(authentication.getName());
        Account originAccount = accountService.findByNumber(originAccountNumber);
        Account destinationAccount = accountService.findByNumber(destinationAccountNumber);


        if (amount.isNaN() || amount <= 0){

            return new ResponseEntity<>("Missing information: amount", HttpStatus.FORBIDDEN);

        }

        if (description.isEmpty()){

            return new ResponseEntity<>("Missing information: description", HttpStatus.FORBIDDEN);
        }



        if (originAccountNumber.isEmpty()){

            return new ResponseEntity<>("Missing information: Origin Account Number", HttpStatus.FORBIDDEN);
        }

        if (destinationAccountNumber.isEmpty()){

            return new ResponseEntity<>("Missing information: Destination Account Number", HttpStatus.FORBIDDEN);
        }

        if (originAccountNumber.equals(destinationAccountNumber)){

            return new ResponseEntity<>("The source and destination account numbers are the same.", HttpStatus.FORBIDDEN);

        }

        if (originAccount == null){

            return new ResponseEntity<>("The source account does not exist.", HttpStatus.FORBIDDEN);

        }


        if (!authenticatedClient.getAccounts().contains(originAccount)){

            return new ResponseEntity<>("The source account does not belong to an authenticated customer.", HttpStatus.FORBIDDEN);
        }

        if (destinationAccount == null){

            return new ResponseEntity<>("The destination account does not exist.", HttpStatus.FORBIDDEN);

        }

        if (originAccount.getBalance() < amount){

            return new ResponseEntity<>("Account balance insufficient for transaction", HttpStatus.FORBIDDEN);

        }


        Transaction transaction1 = new Transaction(TransactionType.CREDIT,amount,description,LocalDateTime.now());
        Transaction transaction2 = new Transaction(TransactionType.DEBIT,amount,description,LocalDateTime.now());

        originAccount.addTransaction(transaction2);
        originAccount.setBalance(originAccount.getBalance()-amount);

        destinationAccount.addTransaction(transaction1);
        destinationAccount.setBalance(destinationAccount.getBalance()+amount);

        transactionService.saveTransaction(transaction1);
        transactionService.saveTransaction(transaction2);

        return new ResponseEntity<>("Transaction success" , HttpStatus.CREATED);

    }

}