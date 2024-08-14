package com.bhagya.account.api.service;

import com.bhagya.account.api.model.Account;
import com.bhagya.account.api.model.Transaction;
import com.bhagya.account.api.repository.AccountRepository;
import com.bhagya.account.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service class for account operations.
 */
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Creates a new account with an initial credit.
     *
     * @param customerId the ID of the customer
     * @param initialCredit the initial credit amount
     * @return the created account
     * @throws RuntimeException if account creation fails
     * @example createAccount(1L, BigDecimal.valueOf(100.0)) -> Account{id=1, customerId=1, balance=100.0}
     */
    @Transactional(rollbackFor = Exception.class)
    public Account createAccount(Long customerId, BigDecimal initialCredit) {
        if (customerId == null || initialCredit == null || initialCredit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        Account account = new Account();
        account.setCustomerId(customerId);
        account.setBalance(initialCredit);

        try {
            account = accountRepository.save(account);
            if (initialCredit.compareTo(BigDecimal.ZERO) > 0) {
                Transaction transaction = new Transaction();
                transaction.setAccountId(account.getId());
                transaction.setAmount(initialCredit);
                transaction.setDescription("Initial credit");
                transactionRepository.save(transaction);
            }
            return account;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create account", e);
        }
    }

    /**
     * Retrieves a list of accounts for a customer.
     *
     * @param customerId the ID of the customer
     * @return a list of accounts
     * @throws RuntimeException if account retrieval fails
     * @example getAccounts(1L) -> [Account{id=1, customerId=1, balance=100.0}, Account{id=2, customerId=1, balance=200.0}]
     */
    public List<Account> getAccounts(Long customerId) {
        if (customerId == null || customerId <= 0) {
            throw new IllegalArgumentException("Invalid customer ID");
        }

        try {
            return accountRepository.findByCustomerId(customerId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve accounts", e);
        }
    }
}