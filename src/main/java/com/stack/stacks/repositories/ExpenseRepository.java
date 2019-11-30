package com.stack.stacks.repositories;

import com.stack.stacks.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
//    @Query("SELECT SUM(expenses.amount) FROM expenses")
//    Expense sumOfExpenses ();
}
