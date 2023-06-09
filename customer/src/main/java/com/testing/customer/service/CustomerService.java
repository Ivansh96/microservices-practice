package com.testing.customer.service;

import com.testing.amqp.producer.RabbitMQMessageProducer;
import com.testing.clients.fraud.FraudCheckResponse;
import com.testing.clients.fraud.FraudClient;
import com.testing.clients.notification.NotificationClient;
import com.testing.clients.notification.NotificationRequest;
import com.testing.customer.dal.repository.CustomerRepository;
import com.testing.customer.dto.CustomerRequest;
import com.testing.customer.dal.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate,
                              FraudClient fraudClient,
                              RabbitMQMessageProducer rabbitMQMessageProducer) {
    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: email validation

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        // todo: custom exception
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Welcome %s",
                        customer.getFirstName())
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
