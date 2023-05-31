package com.testing.fraud.dal.repository;

import com.testing.fraud.dal.model.FraudHistoryCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudHistoryCheckRepository extends JpaRepository<FraudHistoryCheck, Integer> {
}
