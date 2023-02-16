package com.bank.bank;

import com.bank.bank.enums.AccountStatus;
import com.bank.bank.models.AccountHolder;
import com.bank.bank.models.Address;
import com.bank.bank.models.DTO.AccountDTO;
import com.bank.bank.models.Savings;
import com.bank.bank.repositories.AccountHolderRepository;
import com.bank.bank.repositories.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @BeforeEach
    void setUP() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Address address1 = new Address("c/mallorca","barcelona", "españa","08026");
        Address address2 = new Address("c/provença","barcelona","españa","08026");
        AccountHolder accountHolder1 = accountHolderRepository.save(new AccountHolder("dani", LocalDate.of(1980,1,15),address1,address2));

        Savings savings1 = new Savings(BigDecimal.valueOf(222222),"1234", accountHolder1,null,BigDecimal.valueOf(10), AccountStatus.ACTIVE,BigDecimal.valueOf(22),BigDecimal.valueOf(300));
        accountRepository.save(savings1);
    }
    @AfterEach
    void tearDown(){
        accountRepository.deleteAll();
    }
    @Test
    void shouldReturnSavings_WhenSavingsIsCalled() throws Exception {
        AccountDTO accountDTO = new AccountDTO("400","1234",1,1,"5","300","40", "1000", "40");
        String body = objectMapper.writeValueAsString(accountDTO);
        //System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/new-savingsAccount").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        System.err.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolderRepository.findById(1).get().getName()));

    }
    @Test
    void shouldAddNewSavingAccount_WhenPostIsPerformed() throws Exception {
        Address address3 = new Address("c/garcilaso","barcelona","españa","08027");
        String body = objectMapper.writeValueAsString(address3);
        MvcResult mvcResult = mockMvc.perform(post("/new-savingsAccount").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("c/garcilaso"));
    }


}
