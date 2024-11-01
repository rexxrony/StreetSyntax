package com.rex.streetSyntax.service;

import com.rex.streetSyntax.entity.User;
import com.rex.streetSyntax.exceptions.ResourceNotFoundException;
import com.rex.streetSyntax.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public String updateUserDetails(User updatedUser) {
        logger.info("Updating user details for user ID: {}", updatedUser.getUserid());

        Optional<User> userOptional = userRepository.findById(updatedUser.getUserid());
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            // Update fields
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());

            // Update password only if provided and meets criteria
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                if (updatedUser.getPassword().length() < 8) {
                    throw new ResourceNotFoundException("Password must be at least 8 characters long");
                }
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            userRepository.save(existingUser);  // Save updated user details
            logger.debug("User details updated for user ID: {}", updatedUser.getUserid());
            return "User Details Updated Successfully!";
        } else {
            throw new ResourceNotFoundException("User not found with ID: " + updatedUser.getUserid());
        }
    }

    public String registerUser(User user) {
        logger.info("Registering user with username: {}", user.getUsername());

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new ResourceNotFoundException("Username is required!");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ResourceNotFoundException("Email is required!");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().length() < 8) {
            throw new ResourceNotFoundException("Password must be at least 8 characters long");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); // Default role is USER
        userRepository.save(user);
        return "User Registration Successful!";
    }

    public String userLogin(String email, String password) {
        logger.info("User login attempt with email: {}", email);

        // Retrieve user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Verify password
        if (passwordEncoder.matches(password, user.getPassword())) {
            logger.info("Login successful for email: {}", email);
            return "User Login Successful";
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
