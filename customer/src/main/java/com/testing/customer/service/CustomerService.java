package com.testing.customer.service;

import com.testing.customer.dal.repository.CustomerRepository;
import com.testing.customer.dto.CustomerRequest;
import com.testing.customer.dal.model.Customer;
import com.testing.customer.dto.FraudCheckResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate) {
    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: email validation

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/isFraudster/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        // todo: custom exception
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }
}
