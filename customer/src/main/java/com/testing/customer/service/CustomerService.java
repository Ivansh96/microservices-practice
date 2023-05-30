package com.testing.customer.service;

import com.testing.customer.dto.CustomerRequest;
import com.testing.customer.model.Customer;
import org.springframework.stereotype.Service;

@Service
public record CustomerService() {
    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: email validation
        // todo: customer to db
    }
}
