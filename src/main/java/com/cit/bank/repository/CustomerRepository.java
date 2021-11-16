package com.cit.bank.repository;

import com.cit.bank.domain.Customer;

import java.util.Arrays;
import java.util.List;

public class CustomerRepository {

    private static CustomerRepository customerRepository;

    private final List<Customer> allCustomers;

    private CustomerRepository() {
        this.allCustomers = generateCustomers();
    }

    public Customer findById(final Integer identifierToSearch) {
        return this.allCustomers.stream()
                .filter(c -> c.getIdentifier().equals(identifierToSearch))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    private List<Customer> generateCustomers() {
        return Arrays.asList(
                new Customer(1, "João"),
                new Customer(2, "Lucas"),
                new Customer(3, "Pedro"),
                new Customer(4, "Cristiano"),
                new Customer(5, "Rafael")
        );
    }

    public static CustomerRepository getInstance() {
        if (customerRepository == null)
            customerRepository = new CustomerRepository();
        return customerRepository;
    }
}
