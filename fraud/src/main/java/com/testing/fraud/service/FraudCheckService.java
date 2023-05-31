package com.testing.fraud.service;

import com.testing.fraud.dal.model.FraudHistoryCheck;
import com.testing.fraud.dal.repository.FraudHistoryCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
    private final FraudHistoryCheckRepository fraudRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudRepository.save(
                FraudHistoryCheck.builder()
                        .customerId(customerId)
                        .IsFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
