package com.ExpenseTracker.service;

import com.ExpenseTracker.data.models.Expense;
import com.ExpenseTracker.data.models.User;
import com.ExpenseTracker.data.repositories.ExpenseRepository;
import com.ExpenseTracker.data.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
        if (expense.getDate() == null) {
            expense.setDate(LocalDate.now());
        }
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

    public Expense updateExpense(Long userId, Long expenseId, Expense updatedExpense) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Expense existingExpense = expenseRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!existingExpense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Expense does not belong to this user");
        }
        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setDate(updatedExpense.getDate());
        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long userId, Long expenseId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!expense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Expense does not belong to this user");
        }
        expenseRepository.delete(expense);
    }
}
