package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;

import java.util.List;

public interface AccountService {


    public List<AccountDTO> getAccountsDTO();

    public AccountDTO getAccountDTO(Long id);

    public void saveAccount(Account account);

    public Client FindByEmail(String email);

    public Account findByNumber(String string);
}
