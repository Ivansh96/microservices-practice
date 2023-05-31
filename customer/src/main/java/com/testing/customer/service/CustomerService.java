package com.testing.customer.service;

import com.testing.customer.dal.repository.CustomerRepository;
import com.testing.customer.dto.CustomerRequest;
import com.testing.customer.dal.model.Customer;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: email validation

        customerRepository.save(customer);
    }
}
