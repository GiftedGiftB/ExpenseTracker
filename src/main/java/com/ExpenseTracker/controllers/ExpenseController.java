package com.ExpenseTracker.controllers;

import com.ExpenseTracker.data.models.Expense;
import com.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/expenses")
public class ExpenseController {


    private final ExpenseService expenseService;

    @Autowired
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

    @PutMapping("/{expenseId}")
    public Expense updateExpense(@PathVariable Long userId, @PathVariable Long expenseId, @RequestBody Expense updatedExpense) {
        return expenseService.updateExpense(userId, expenseId, updatedExpense);
    }

    @DeleteMapping("/{expenseId}")
    public String deleteExpense(@PathVariable Long userId, @PathVariable Long expenseId) {
        expenseService.deleteExpense(userId, expenseId);
        return "Expense deleted successfully.";

    }
}
