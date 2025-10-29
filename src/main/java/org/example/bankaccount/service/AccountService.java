package org.example.bankaccount.service;

import org.example.bankaccount.enums.Status;
import org.example.bankaccount.module.Account;
import org.example.bankaccount.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Yeni hesab yaratmaq
    public Account createAccount(Account account) {
        account.setLocalDateTime(LocalDateTime.now());
        if (account.getStatus() == null) {
            account.setStatus(Status.ACTIVE);
        }
        return accountRepository.save(account);
    }

    // Bütün hesabları göstərmək
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // ID ilə hesab tapmaq
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    // Hesabı silmək
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        accountRepository.delete(account);
    }

    // Status dəyişmək
    public Account updateStatus(Long id, String newStatus) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        try {
            account.setStatus(Status.valueOf(newStatus.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status value");
        }
        return accountRepository.save(account);
    }
}
