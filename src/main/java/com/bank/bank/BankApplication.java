package com.bank.bank;

import com.bank.bank.enums.AccountStatus;
import com.bank.bank.models.*;
import com.bank.bank.repositories.AccountHolderRepository;
import com.bank.bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BankApplication implements CommandLineRunner {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountHolderRepository accountHolderRepository;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*


		Address address1 = new Address("c/mallorca","barcelona", "españa","08026");
		Address address2 = new Address("c/provença","barcelona","españa","08026");

		AccountHolder accountHolder1 = new AccountHolder("dani", LocalDate.of(1980,1,15),address1,address2);
		accountHolderRepository.save(accountHolder1);
		AccountHolder accountHolder2 = new AccountHolder("vanesa",LocalDate.of(1990,9,6),address1,address2);
		accountHolderRepository.save(accountHolder2);


		CreditCard creditCard1 = new CreditCard(BigDecimal.valueOf(111111),"1234",accountHolder1,null,BigDecimal.valueOf(12),AccountStatus.ACTIVE,BigDecimal.valueOf(300000),BigDecimal.valueOf(12));
		accountRepository.save(creditCard1);
		CreditCard creditCard2 = new CreditCard(BigDecimal.valueOf(222222),"4567",accountHolder2,null,BigDecimal.valueOf(12),AccountStatus.ACTIVE,BigDecimal.valueOf(300000),BigDecimal.valueOf(12));
		accountRepository.save(creditCard2);


		Savings savings1 = new Savings(BigDecimal.valueOf(222222),"1234", accountHolder1,null,BigDecimal.valueOf(10),AccountStatus.ACTIVE,BigDecimal.valueOf(222222),BigDecimal.valueOf(12));
		accountRepository.save(savings1);
		Savings savings2 = new Savings(BigDecimal.valueOf(333333),"9876", accountHolder2,null,BigDecimal.valueOf(11),AccountStatus.ACTIVE,BigDecimal.valueOf(444444),BigDecimal.valueOf(12));
		accountRepository.save(savings2);

		CheckingAccount checkingAccount1 = new CheckingAccount(BigDecimal.valueOf(333333),"32323",accountHolder1, null, BigDecimal.valueOf(22),AccountStatus.ACTIVE,BigDecimal.valueOf(1));
		accountRepository.save(checkingAccount1);
		CheckingAccount checkingAccount2 = new CheckingAccount(BigDecimal.valueOf(555555),"434343",accountHolder2, null, BigDecimal.valueOf(19),AccountStatus.ACTIVE,BigDecimal.valueOf(1));
		accountRepository.save(checkingAccount2);
		StudentChecking studentChecking1 = new StudentChecking(BigDecimal.valueOf(700),"242424",accountHolder1, null, BigDecimal.valueOf(0),AccountStatus.ACTIVE);
		accountRepository.save(studentChecking1);
		StudentChecking studentChecking2 = new StudentChecking(BigDecimal.valueOf(700),"121212",accountHolder2, null, BigDecimal.valueOf(0),AccountStatus.ACTIVE);
		accountRepository.save(studentChecking2);
		 */

	}
}
