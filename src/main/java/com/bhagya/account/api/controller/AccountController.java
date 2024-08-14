package com.bhagya.account.api.controller;

import com.bhagya.account.api.model.Account;
import com.bhagya.account.api.model.CreateAccountRequest;
import com.bhagya.account.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for account operations.
 */
@RestController
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Creates a new account with an initial credit.
     *
     * @param request the create account request
     * @return the created account
     * @example POST /accounts -> Account{id=1, customerId=1, balance=100.0}
     */
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest request) {
        try {
            Account account = accountService.createAccount(request.getCustomerId(), request.getInitialCredit());
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a list of accounts for a customer.
     *
     * @param customerId the ID of the customer
     * @return a list of accounts
     * @example GET /accounts/1 -> [Account{id=1, customerId=1, balance=100.0}, Account{id=2, customerId=1, balance=200.0}]
     */
    @GetMapping("/accounts/{customerId}")
    public ResponseEntity<List<Account>> getAccounts(@PathVariable Long customerId) {
        try {
            List<Account> accounts = accountService.getAccounts(customerId);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}