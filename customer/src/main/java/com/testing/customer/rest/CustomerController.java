package com.testing.customer.rest;

import com.testing.customer.dto.CustomerRequest;
import com.testing.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {
    @PostMapping("/register")
    public void registerCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("new customer registration {}", customerRequest);
        customerService.registerCustomer(customerRequest);
    }
}
