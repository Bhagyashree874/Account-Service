package com.account.api.test;

import com.bhagya.account.api.model.Account;
import com.bhagya.account.api.repository.AccountRepository;
import com.bhagya.account.api.repository.TransactionRepository;
import com.bhagya.account.api.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;

    @Test
    void createAccount() {
        // Arrange
        Long customerId = 1L;
        BigDecimal initialCredit = BigDecimal.valueOf(100.0);
        Account account = new Account();
        account.setId(1L);
        account.setCustomerId(1L);
        account.setBalance(BigDecimal.valueOf(100.0));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // Act
        Account createdAccount = accountService.createAccount(customerId, initialCredit);

        // Assert
        assertEquals(account, createdAccount);
    }

    @Test
    void createAccount_InvalidInput() {
        // Arrange
        Long customerId = null;
        BigDecimal initialCredit = BigDecimal.valueOf(-100.0);

        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> accountService.createAccount(customerId, initialCredit));
        assertEquals("Invalid input parameters", exception.getMessage());
    }

    @Test
    void getAccounts() {
        // Arrange
        Long customerId = 1L;
        Account account = new Account();
        account.setId(1L);
        account.setCustomerId(1L);
        account.setBalance(BigDecimal.valueOf(100.0));
        Account account1 = new Account();
        account1.setId(2L);
        account1.setCustomerId(1L);
        account1.setBalance(BigDecimal.valueOf(200.0));
        List<Account> accounts = List.of(account,account1);
        when(accountRepository.findByCustomerId(customerId)).thenReturn(accounts);

        // Act
        List<Account> retrievedAccounts = accountService.getAccounts(customerId);

        // Assert
        assertEquals(accounts, retrievedAccounts);
    }
}