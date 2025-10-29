package org.example.bankaccount;



import org.example.bankaccount.module.Account;
import org.example.bankaccount.repository.AccountRepository;
import org.example.bankaccount.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void testGetAllAccounts() {
        Account account = new Account();
        account.setId(1L);
        account.setFirstName("Elcin");

        when(accountRepository.findAll()).thenReturn(List.of(account));

        List<Account> result = accountService.getAllAccounts();

        assertEquals(1, result.size());
        assertEquals("Elcin", result.get(0).getFirstName());
    }

    @Test
    void testGetAccountById() {
        Account account = new Account();
        account.setId(1L);
        account.setFirstName("Elcin");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Optional<Account> result = accountService.getAccountById(1L);

        assertTrue(result.isPresent());
        assertEquals("Elcin", result.get().getFirstName());
    }

    @Test
    void testCreateAccount() {
        Account account = new Account();
        account.setFirstName("Elcin");

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account saved = accountService.createAccount(account);

        assertNotNull(saved);
        assertEquals("Elcin", saved.getFirstName());
        verify(accountRepository, times(1)).save(account);
    }
}
