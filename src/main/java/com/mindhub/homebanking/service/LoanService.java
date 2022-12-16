package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.Loan;
import java.util.List;

public interface LoanService {


    public List<LoanDTO> getLoanDTO();


    public Loan getLoan(Long id);

    public Loan findById(long id);
}
