package com.atlen.app.repository;

import com.atlen.app.entity.BudgetItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetItem, Long> {
    List<BudgetItem> findByTripId(Long tripId);
}
