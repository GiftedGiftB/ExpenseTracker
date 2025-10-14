package com.ExpenseTracker;

import com.ExpenseTracker.data.models.Expense;
import com.ExpenseTracker.data.models.User;
import com.ExpenseTracker.data.repositories.ExpenseRepository;
import com.ExpenseTracker.data.repositories.UserRepository;
import com.ExpenseTracker.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExpenseService expenseService;

    private User user;
    private Expense expense;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        expense = new Expense();
        expense.setId(1L);
        expense.setTitle("Lunch");
        expense.setAmount(20.5);
        expense.setUser(user);
        expense.setDate(LocalDate.now());
    }

    @Test
    void testAddExpense() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);
        Expense savedExpense = expenseService.addExpense(1L, expense);
        assertNotNull(savedExpense);
        assertEquals("Lunch", savedExpense.getTitle());
        verify(expenseRepository, times(1)).save(any(Expense.class));
    }

    @Test
    void testGetExpenses() {
        when(expenseRepository.findByUserId(1L)).thenReturn(List.of(expense));
        List<Expense> result = expenseService.getExpenses(1L);
        assertEquals(1, result.size());
        assertEquals("Lunch", result.get(0).getTitle());
        verify(expenseRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetTotalExpenses() {
        when(expenseRepository.findByUserId(1L)).thenReturn(Arrays.asList(expense));
        Double total = expenseService.getTotalExpenses(1L);
        assertEquals(20.5, total);
    }

    @Test
    void testUpdateExpense() {
        Expense updatedExpense = new Expense();
        updatedExpense.setTitle("Dinner");
        updatedExpense.setAmount(30.0);
        updatedExpense.setDate(LocalDate.now());
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));
        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);
        Expense result = expenseService.updateExpense(1L, 1L, updatedExpense);
        assertEquals("Dinner", result.getTitle());
        assertEquals(30.0, result.getAmount());
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    void testDeleteExpense() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));
        expenseService.deleteExpense(1L, 1L);
        verify(expenseRepository, times(1)).delete(expense);
    }

    @Test
    void testAddExpense_UserNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> expenseService.addExpense(99L, expense));
    }

}
