package com.stack.stacks.controller;

import com.stack.stacks.models.Expense;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class AjaxController {
    @Autowired
    private ExpenseRepository expenseDao;

    @PostMapping("/expenses.json")
    @ResponseBody
    public String createExpenseWithAjax(@Valid
            @RequestBody (required = false) Map<String, Object> expense
    ) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        double amount = Double.parseDouble((String) expense.get("amount"));
        String date = (String) expense.get("date");
        boolean isRegular = Boolean.parseBoolean ((String) expense.get("isRegular"));
        int type = Integer.parseInt((String) expense.get("type"));
        String description = (String) expense.get("description");
        Expense newExpense = new Expense();
        newExpense.setAmount(amount);
        newExpense.setDate(date);
        newExpense.setRegular(isRegular);
        newExpense.setType(type);
        newExpense.setDescription(description);
        newExpense.setUser(currentUser);
        expenseDao.save(newExpense);
        return "Yay";
    }

}
