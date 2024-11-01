package com.rex.streetSyntax.controller;

import com.rex.streetSyntax.entity.User;
import com.rex.streetSyntax.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        logger.info("Registering user with username: {}", user.getUsername());
        String response = userService.registerUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUserDetails(@RequestBody User user) {
        logger.info("Updating details for user ID: {}", user.getUserid());
        String response = userService.updateUserDetails(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
            logger.info("User login attempt with email: {}", email);
            String response = userService.userLogin(email, password);

            // Set a session attribute to indicate the user is logged in
          request.getSession().setAttribute("AUTHENTICATED_USER", email);
          return new ResponseEntity<>(response, HttpStatus.OK);
}

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        logger.info("User logged out successfully");
        return new ResponseEntity<>("Logout successful!", HttpStatus.OK);
    }
}
