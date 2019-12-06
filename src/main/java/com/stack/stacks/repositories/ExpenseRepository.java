package com.stack.stacks.repositories;

import com.stack.stacks.models.Expense;
import com.stack.stacks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query(value = "SELECT sum(amount) FROM expenses WHERE user_id = ?1 ", nativeQuery = true)
    public double sumOfExpenses(Long id);

    @Query(value = "SELECT * from expenses order by date desc limit 5", nativeQuery = true)
    public List<Expense> firstFiveExpenses();
}
