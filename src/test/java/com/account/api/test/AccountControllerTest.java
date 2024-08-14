package com.account.api.test;

import com.bhagya.account.api.controller.AccountController;
import com.bhagya.account.api.model.Account;
import com.bhagya.account.api.model.CreateAccountRequest;
import com.bhagya.account.api.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Test
    void createAccount() {
        // Arrange
        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerId(1L);
        request.setInitialCredit(BigDecimal.valueOf(100.0));
        Account account = new Account();
        account.setId(1L);
        account.setCustomerId(1L);
        account.setBalance(BigDecimal.valueOf(100.0));
        when(accountService.createAccount(1L, BigDecimal.valueOf(100.0))).thenReturn(account);

        // Act
        ResponseEntity<Account> response = accountController.createAccount(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(account, response.getBody());
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
        List<Account> accounts = Arrays.asList(account, account1);
        when(accountService.getAccounts(customerId)).thenReturn(accounts);

        // Act
        ResponseEntity<List<Account>> response = accountController.getAccounts(customerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accounts, response.getBody());
    }
}