package com.ExpenseTracker.service;

import com.ExpenseTracker.data.models.User;
import com.ExpenseTracker.data.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User loginUser(String email, String password) {
        Optional<User> foundUser = userRepository.findByEmailAndPassword(email, password);
        return foundUser.orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }

}
