package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanAplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    ClientService clientService;

    @Autowired
    LoanService loanService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    ClientLoanService clientLoanService;

    @RequestMapping("/loans")
    public List<LoanDTO> getLoanDTO(){
        return loanService.getLoanDTO();
    }


    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> createLoan (Authentication authentication,@RequestBody LoanAplicationDTO loanAplicationDTO) {

        Client client = clientService.findByEmail(authentication.getName());
        Loan prestamoSeleccionado = loanService.findById(loanAplicationDTO.getId());
        Account account = accountService.findByNumber(loanAplicationDTO.getAccountDestinit());

        //Set<ClientLoan> clientLoan2 = client.getClientLoans().stream().filter(clientLoan1 -> clientLoan1.getLoan().getName().equals(loan2.getName())).collect(Collectors.toSet());


        if (loanAplicationDTO.getId() <= 0) {
            return new ResponseEntity<>("Id no exist", HttpStatus.FORBIDDEN);
        }
        if (loanAplicationDTO.getAmount() <= 0 || loanAplicationDTO.getAmount().isNaN()) {
            return new ResponseEntity<>("not possible this amount", HttpStatus.FORBIDDEN);
        }
        if (loanAplicationDTO.getPayment() <= 0)
            return new ResponseEntity<>("paid are not valid", HttpStatus.FORBIDDEN);

        if (loanAplicationDTO.getAccountDestinit().isEmpty())
            return new ResponseEntity<>("Destination account is not valid", HttpStatus.FORBIDDEN);

        if(prestamoSeleccionado == null){
            return new ResponseEntity<>("The loan does not exist", HttpStatus.FORBIDDEN);
        }
        if(prestamoSeleccionado.getMaxAmount() <= loanAplicationDTO.getAmount()) {
            return new ResponseEntity<>("The loan amount requested exceeds the maximum loan amount available.",HttpStatus.FORBIDDEN);
        }
        if(!prestamoSeleccionado.getPayments().contains(loanAplicationDTO.getPayment())){
            return new ResponseEntity<>("the requested quota is not avaible",HttpStatus.FORBIDDEN);
        }
        if(account == null){
            return new ResponseEntity<>("The target account does not exist",HttpStatus.FORBIDDEN);
        }
        if(!client.getAccounts().contains(account)){
            return new ResponseEntity<>("the destination account does not belong to the customer",HttpStatus.FORBIDDEN);
        }

        if (client.getClientLoans().stream().filter(clientLoan -> clientLoan.getLoan().getName().equals(prestamoSeleccionado.getName())).toArray().length == 1){
            return new ResponseEntity<>("You already have a loan of this type.", HttpStatus.FORBIDDEN);
        }

        if(prestamoSeleccionado.getName().equals("Mortgage")){
            ClientLoan clientLoan3 = new ClientLoan(client, prestamoSeleccionado, loanAplicationDTO.getAmount() * 1.2,loanAplicationDTO.getPayment());
            clientLoanService.save(clientLoan3);
        }

        if(prestamoSeleccionado.getName().equals("Personal")){
            ClientLoan clientLoan4 = new ClientLoan(client, prestamoSeleccionado, loanAplicationDTO.getAmount() * 1.4,loanAplicationDTO.getPayment());
            clientLoanService.save(clientLoan4);
        }

        if(prestamoSeleccionado.getName().equals("Automotive")){

            ClientLoan clientLoan5 = new ClientLoan(client, prestamoSeleccionado, loanAplicationDTO.getAmount() * 1.6,loanAplicationDTO.getPayment());
            clientLoanService.save(clientLoan5);

        }

        account.setBalance(account.getBalance() + loanAplicationDTO.getAmount());
        Transaction transaction = new Transaction(TransactionType.CREDIT,loanAplicationDTO.getAmount(), prestamoSeleccionado.getName() + " " +"Loan approved", LocalDateTime.now());
        account.addTransaction(transaction);
        transactionService.saveTransaction(transaction);


        return new ResponseEntity<>(client , HttpStatus.CREATED) ;
    }

}
