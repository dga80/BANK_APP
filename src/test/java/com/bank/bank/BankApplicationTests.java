package com.bank.bank;

import com.bank.bank.enums.AccountStatus;
import com.bank.bank.models.AccountHolder;
import com.bank.bank.models.Address;
import com.bank.bank.models.Savings;
import com.bank.bank.repositories.AccountHolderRepository;
import com.bank.bank.repositories.AccountRepository;
import com.bank.bank.services.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankApplicationTests {
	@Autowired
	WebApplicationContext context;
	private MockMvc mockMvc;
	private ObjectMapper ObjectMapper = new ObjectMapper();
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountHolderRepository accountHolderRepository;


	@Test
	void contextLoads() {
	}
	@Test
	public void createSavingAccount() {
		Address address1 = new Address("c/mallorca","barcelona", "españa","08026");
		Address address2 = new Address("c/provença","barcelona","españa","08026");
		AccountHolder accountHolder1 = accountHolderRepository.save(new AccountHolder("dani", LocalDate.of(1980,1,15),address1,address2));

		Savings savings1 = new Savings(BigDecimal.valueOf(222222),"1234", accountHolder1,null,BigDecimal.valueOf(10), AccountStatus.ACTIVE,BigDecimal.valueOf(22),BigDecimal.valueOf(300));
		accountRepository.save(savings1);

		BigDecimal interestRate = new BigDecimal("0.05");
		BigDecimal minimumBalance = new BigDecimal("500");
		Savings savingsAccount = new Savings();

		assertEquals(interestRate, savingsAccount.getInterestRate());
		assertEquals(minimumBalance, savingsAccount.getMinimumBalance());
	}


}
