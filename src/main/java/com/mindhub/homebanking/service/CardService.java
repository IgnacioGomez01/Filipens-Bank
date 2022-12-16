package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface CardService {



    public void saveCard(Card card);


}
