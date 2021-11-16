package com.cit.bank.repository;

import com.cit.bank.domain.Account;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class AccountRepository {

    private static AccountRepository accountRepository;

    private final List<Account> allAccounts;
    private final CustomerRepository customerRepository;

    private AccountRepository(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        allAccounts = generateAccounts();
    }

    public Account findById(final Integer identifierToSearch) {
        return this.allAccounts.stream()
                .filter(c -> c.getIdentifier().equals(identifierToSearch))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    private List<Account> generateAccounts() {
        return Arrays.asList(
                new Account(1, customerRepository.findById(1), new BigDecimal("100")),
                new Account(2, customerRepository.findById(2), new BigDecimal("100")),
                new Account(3, customerRepository.findById(3), new BigDecimal("100")),
                new Account(4, customerRepository.findById(4), new BigDecimal("100")),
                new Account(5, customerRepository.findById(5), new BigDecimal("100"))
        );
    }

    public static AccountRepository getInstance() {
        if (accountRepository == null)
            accountRepository = new AccountRepository(CustomerRepository.getInstance());
        return accountRepository;
    }
}
