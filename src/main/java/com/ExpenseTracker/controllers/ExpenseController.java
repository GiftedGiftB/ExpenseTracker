package com.ExpenseTracker.controllers;

import com.ExpenseTracker.data.models.Expense;
import com.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> updateExpense(@PathVariable Long userId, @PathVariable Long expenseId, @RequestBody Expense updatedExpense) {
        try {
            Expense expense = expenseService.updateExpense(userId, expenseId, updatedExpense);
            return ResponseEntity.ok(expense);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{expenseId}")
    public String deleteExpense(@PathVariable Long userId, @PathVariable Long expenseId) {
        expenseService.deleteExpense(userId, expenseId);
        return "Expense deleted successfully.";

    }
}
