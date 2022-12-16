package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api")

public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ClientService clientService;

    @RequestMapping("/account")
    public List<AccountDTO> getAccounts(){
        return accountService.getAccountsDTO();

    }
    @GetMapping("/account/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return accountService.getAccountDTO(id);
    }

//---------------------------------------------------------------------------------------------------------//

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> newAccount(Authentication elAutenticado) {

        Client clienteAutenticado = clientService.findByEmail(elAutenticado.getName()); // obtengo la informacion del cliente autenticado, de mi base de datos de client repository

        if ( clienteAutenticado.getAccounts().size() >= 3 ){
        return new ResponseEntity<>("You have a 3 accounts", HttpStatus.FORBIDDEN);

    }else { //De lo contrario se debe crear la cuenta, asignarla al cliente obtenido y guardarla, así como retornar una respuesta “201 creada”

        Account CreateAccount = new Account("VIN" + getRandomNumber(1 , 100000000), LocalDateTime.now(), 0.00);
        clienteAutenticado.addAccounts(CreateAccount);
        accountService.saveAccount(CreateAccount);
        return new ResponseEntity<>("You Created Succefull Account", HttpStatus.CREATED);

    }
}

}
