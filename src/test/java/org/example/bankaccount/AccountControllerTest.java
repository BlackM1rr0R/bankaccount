package org.example.bankaccount.controller;

import org.example.bankaccount.module.Account;
import org.example.bankaccount.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void testGetAllAccounts() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setFirstName("Elcin");

        Mockito.when(accountService.getAllAccounts()).thenReturn(List.of(account));

        mockMvc.perform(get("/accounts/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Elcin")));
    }

    @Test
    void testGetAccountById() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setFirstName("Elcin");

        Mockito.when(accountService.getAccountById(1L)).thenReturn(Optional.of(account));

        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Elcin")));
    }

    @Test
    void testCreateAccount() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setFirstName("Elcin");

        Mockito.when(accountService.createAccount(Mockito.any(Account.class))).thenReturn(account);

        mockMvc.perform(post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Elcin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Elcin")));
    }
}
