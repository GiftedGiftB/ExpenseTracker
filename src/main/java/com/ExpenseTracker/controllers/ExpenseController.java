package com.ExpenseTracker.controllers;

import com.ExpenseTracker.data.models.Expense;
import com.ExpenseTracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense addExpense(@PathVariable Long userId, @RequestBody Expense expense) {
        return expenseService.addExpense(userId, expense);
    }

    @GetMapping
    public List<Expense> getExpenses(@PathVariable Long userId) {
        return expenseService.getExpenses(userId);
    }

    @GetMapping("/summary")
    public Double getTotalExpenses(@PathVariable Long userId){
        return expenseService.getTotalExpenses(userId);
    }
}
