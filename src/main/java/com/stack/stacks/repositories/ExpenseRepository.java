package com.stack.stacks.repositories;

import com.stack.stacks.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
