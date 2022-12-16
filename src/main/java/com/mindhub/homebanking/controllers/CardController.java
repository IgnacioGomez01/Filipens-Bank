package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class CardController {

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Autowired
    CardService cardService;

    @Autowired
    ClientService clientService;

    @PostMapping("/clients/current/cards")

    public ResponseEntity<Object> CreateCard(Authentication authentication, @RequestParam CardType type, @RequestParam CardColor color){
        Client client = clientService.findByEmail(authentication.getName());
        Set<Card> cardClient = client.getCard().stream().filter(card -> card.getType() == type).collect(Collectors.toSet());

        if (cardClient.size() >= 3) {
            return new ResponseEntity<>("you can't create more cards", HttpStatus.FORBIDDEN);
        } else{

            Card CreateCard = new Card(client, type, color, getRandomNumber(1000,10000) +" " + getRandomNumber(1000,10000) +" "+getRandomNumber(1000,10000) +" " + getRandomNumber(1000,10000), getRandomNumber(100,1000), LocalDate.now(), LocalDate.now().plusYears(5)) ;
            cardService.saveCard(CreateCard);
            return new ResponseEntity<>("You Created Succefull Card", HttpStatus.CREATED);

        }
    }


}
