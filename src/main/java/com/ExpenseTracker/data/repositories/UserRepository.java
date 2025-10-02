package com.ExpenseTracker.data.repositories;

import com.ExpenseTracker.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
