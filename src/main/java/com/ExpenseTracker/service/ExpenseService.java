package com.ExpenseTracker.service;


import com.ExpenseTracker.data.models.Expense;
import com.ExpenseTracker.data.models.User;
import com.ExpenseTracker.data.repositories.ExpenseRepository;
import com.ExpenseTracker.data.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public Expense addExpense(Long userId, Expense expense) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpenses(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public Double getTotalExpenses(Long userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return expenses.stream().map(Expense::getAmount).filter(Objects::nonNull).reduce(0.0, Double::sum);
    }

}
