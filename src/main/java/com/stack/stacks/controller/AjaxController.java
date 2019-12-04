package com.stack.stacks.controller;

import com.stack.stacks.models.Expense;
import com.stack.stacks.models.Tag;
import com.stack.stacks.models.User;
import com.stack.stacks.repositories.ExpenseRepository;
import com.stack.stacks.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class AjaxController {
    @Autowired
    private ExpenseRepository expenseDao;

    @Autowired
    private TagRepository tagDao;

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

    @GetMapping("/expenses.json")
    @ResponseBody
    public List<Expense> viewAllExpensesInJSONFormat() {
        return expenseDao.findAll();
    }

    @PostMapping("/tags.json")
    @ResponseBody
    public String createTagWithAjax(@Valid
                                        @RequestBody (required = false) Map<String, Object> tag
    ) {
        String name = (String) tag.get("name");
        Tag newTag = new Tag();
        newTag.setName(name);
        tagDao.save(newTag);
        return "Yay";
    }

}
