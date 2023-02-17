package com.bank.bank;

import com.bank.bank.enums.AccountStatus;
import com.bank.bank.models.*;
import com.bank.bank.models.DTO.AccountDTO;
import com.bank.bank.models.DTO.AccountHolderDTO;
import com.bank.bank.models.DTO.ThirdPartyDTO;
import com.bank.bank.models.DTO.TransactionDTO;
import com.bank.bank.repositories.AccountHolderRepository;
import com.bank.bank.repositories.AccountRepository;
import com.bank.bank.repositories.ThirdPartyRepository;
import com.bank.bank.repositories.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BankControllerTest {
    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @BeforeEach
    void setUP() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Address address1 = new Address("c/mallorca","barcelona", "españa","08026");
        Address address2 = new Address("c/provença","barcelona","españa","08026");
        AccountHolder accountHolder1 = accountHolderRepository.save(new AccountHolder("dani", LocalDate.of(1980,1,15),address1,address2));
        AccountHolderDTO accountHolderDTO = new AccountHolderDTO("Gael", LocalDate.of(2019, 9, 6), address1, address2);


        Savings savings1 = new Savings(BigDecimal.valueOf(222222),"1234", accountHolder1,null,BigDecimal.valueOf(10), AccountStatus.ACTIVE,BigDecimal.valueOf(22),BigDecimal.valueOf(300));
        accountRepository.save(savings1);
    }
    @AfterEach
    void tearDown(){
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        thirdPartyRepository.deleteAll();

    }

    @Test
    void shouldAddNewSavingAccount_WhenPostIsPerformed() throws Exception {
        AccountDTO accountDTO = new AccountDTO("400","1234",1,1,"5","300","40", "1000", "40");
        String body = objectMapper.writeValueAsString(accountDTO);

        MvcResult mvcResult = mockMvc.perform(post("/new-savingsAccount").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolderRepository.findById(1).get().getName()));
    }
    @Test
    void shouldAddCheckingAccount_WhenPostIsPerformed() throws Exception {
        AccountDTO accountDTO = new AccountDTO("400","1234",1,1,"5","300","40", "1000", "40");
        String body = objectMapper.writeValueAsString(accountDTO);

        MvcResult mvcResult = mockMvc.perform(post("/new-checkingAccount").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolderRepository.findById(1).get().getName()));
    }

    @Test
    void shouldAddNewCreditAccount_WhenPostIsPerformed() throws Exception {
        AccountDTO accountDTO = new AccountDTO("500","5678",1,2,"6","400","50", "2000", "50");
        String body = objectMapper.writeValueAsString(accountDTO);

        MvcResult mvcResult = mockMvc.perform(post("/new-creditAccount").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        //System.err.println(mvcResult.getResolvedException());
        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolderRepository.findById(1).get().getName()));

    }
    @Test
    void shouldUpdateBalance_WhenValidInputIsProvided() throws Exception {

        AccountDTO accountDTO = new AccountDTO("500","5678",1,2,"6","400","50", "2000", "50");

        String body = objectMapper.writeValueAsString(accountDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/new-balanceAccount").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolderRepository.findById(1).get().getName()));
    }
    @Test
    void shouldMakeTransaction_WhenValidInputIsProvided() throws Exception {
        Account originAccount = new Account();
        originAccount.setBalance(new BigDecimal("1000.00"));

        Account endAccount = new Account();
        endAccount.setBalance(new BigDecimal("500.00"));

        accountRepository.save(originAccount);
        accountRepository.save(endAccount);

        TransactionDTO transactionDTO = new TransactionDTO(originAccount.getId(), endAccount.getId(), "500");

        String body = objectMapper.writeValueAsString(transactionDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/new-transaction").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        //System.err.println(response);
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Transaction transaction = objectMapper.readValue(response, Transaction.class);
        //System.err.println(transaction);

        assertEquals(new BigDecimal("500.00"), accountRepository.findById(originAccount.getId()).get().getBalance());
        assertEquals(new BigDecimal("1000.00"), accountRepository.findById(endAccount.getId()).get().getBalance());
    }
    @Test
    void shouldMakeThirdPartyTransference_WhenValidInputIsProvided() throws Exception{
        Account originAccount = new Account();
        originAccount.setBalance(new BigDecimal("1000.00"));
        originAccount.setSecretKey("secretKey");

        Account endAccount = new Account();
        endAccount.setBalance(new BigDecimal("500.00"));

        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setName("Third Party");
        thirdParty.setHashedKey("hashedKey");

        accountRepository.save(originAccount);
        accountRepository.save(endAccount);
        thirdPartyRepository.save(thirdParty);

        ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO(thirdParty.getHashedKey(),originAccount.getSecretKey(),"-300");

        String body = objectMapper.writeValueAsString(thirdPartyDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/new-ThirdPartyTransference").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        TransactionThirdParty transactionThirdParty = objectMapper.readValue(response, TransactionThirdParty.class);

        assertEquals(new BigDecimal("700.00"), accountRepository.findById(originAccount.getId()).get().getBalance());
    }




}
