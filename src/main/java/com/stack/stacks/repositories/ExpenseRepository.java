package com.stack.stacks.repositories;

import com.stack.stacks.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query(value = "SELECT sum(amount) FROM expenses", nativeQuery = true)
    public Long sumOfExpenses();
}
