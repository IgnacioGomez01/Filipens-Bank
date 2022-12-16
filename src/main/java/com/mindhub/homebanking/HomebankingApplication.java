package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);


	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return args -> {



			/*Client client1 = new Client("Melba", "Lorenzo", "melba@mindhub.com",passwordEncoder.encode("123"));
			Client client2 = new Client("Roberto", "Gomez", "Roberto@gmail.com", passwordEncoder.encode("2211"));


			Account account1 = new Account("Vin001", LocalDateTime.now(), 5000.00);
			Account account2 = new Account("Vin002", LocalDateTime.now(), 7500.00);

			Transaction transaction1 = new Transaction(TransactionType.DEBIT,-200, "Spotify", LocalDateTime.now()) ;
			Transaction transaction2 = new Transaction(TransactionType.CREDIT,+200, "Sell Product", LocalDateTime.now());
			Transaction transaction3 = new Transaction(TransactionType.DEBIT,-150, "Youtube Premium", LocalDateTime.now()) ;
			Transaction transaction4 = new Transaction(TransactionType.DEBIT,-220, "Netflix", LocalDateTime.now()) ;
			Transaction transaction5 = new Transaction(TransactionType.DEBIT,-200, "Taxes", LocalDateTime.now()) ;
			Transaction transaction6 = new Transaction(TransactionType.CREDIT,+200, "Sell Product", LocalDateTime.now());
			Transaction transaction7 = new Transaction(TransactionType.DEBIT,-150, "Car Patent", LocalDateTime.now()) ;
			Transaction transaction8 = new Transaction(TransactionType.DEBIT,-220, "Foods", LocalDateTime.now()) ;

			client1.addAccounts(account1);
			client1.addAccounts(account2);

			account1.addTransaction(transaction1);
			account1.addTransaction(transaction2);
			account1.addTransaction(transaction3);
			account1.addTransaction(transaction4);
			account2.addTransaction(transaction5);
			account2.addTransaction(transaction6);
			account2.addTransaction(transaction7);
			account2.addTransaction(transaction8);

			clientRepository.save(client1);
			clientRepository.save(client2);

			accountRepository.save(account1);
			accountRepository.save(account2);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);
			transactionRepository.save(transaction7);
			transactionRepository.save(transaction8);

			//============================== Loan =============================//
			Loan loan1 = new Loan("Mortgage", 500000, List.of(12,24,36,48,60));
			Loan loan2 = new Loan("Personal", 100000, List.of(6,12,24));
			Loan loan3 = new Loan("Automotive", 300000, List.of(6,12,24,36));

			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);


			//============================== ClientLoan =============================//
			ClientLoan clientLoan1 = new ClientLoan(client1, loan1, 400000, 60);
			ClientLoan clientLoan2 = new ClientLoan(client1, loan2, 50000, 12);

			ClientLoan clientLoan3 = new ClientLoan(client2, loan2, 100000, 24);
			ClientLoan clientLoan4 = new ClientLoan(client2, loan3, 200000, 36);

			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);


			//============================== Cards =============================//

			Card card1 = new Card(client1, CardType.DEBIT, CardColor.GOLD, "7777 7777 7777 7777", 270, LocalDate.now(), LocalDate.now().plusYears(5));
			Card card2 = new Card(client1, CardType.CREDIT, CardColor.TITANIUM, "5555 5555 5555 5555", 151, LocalDate.now(), LocalDate.now().plusYears(5));
			Card card3 = new Card(client2, CardType.CREDIT, CardColor.SILVER, "1776 2419 5120 7777", 257, LocalDate.now(), LocalDate.now().plusYears(5));


			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);

			*/
		};

	}

}
