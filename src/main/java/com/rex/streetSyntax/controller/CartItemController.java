package com.rex.streetSyntax.controller;

import com.rex.streetSyntax.entity.CartItem;
import com.rex.streetSyntax.service.CartItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    private static final Logger logger = LoggerFactory.getLogger(CartItemController.class);


    @PostMapping
    public ResponseEntity<String> addItemToCart(@RequestParam Long prodId, @RequestParam int quantity, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("AUTHENTICATED_USER");
        if (username == null) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }

        logger.info("Adding product with ID: {} to cart for user: {}", prodId, username);
        String response = cartItemService.addItemToCart(username, prodId, quantity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> deleteItemFromCart(@PathVariable long cartItemId, HttpServletRequest request) {
         String email = (String) request.getSession().getAttribute("AUTHENTICATED_USER");
         if (email == null) {
                 return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
         }

        logger.info("Deleting item with ID: {} from cart for user: {}", cartItemId, email);
        String response = cartItemService.deleteItemFromCart(email, cartItemId);
        logger.debug("Item removed successfully from cart for user: {}", email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

@GetMapping
public ResponseEntity<List<CartItem>> getAllCartItems(HttpServletRequest request) {
    String email = (String) request.getSession().getAttribute("AUTHENTICATED_USER");
    if (email == null) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    logger.info("Fetching all cart items for user: {}", email);
    List<CartItem> response = cartItemService.getAllCartItems(email);
    logger.debug("Fetched {} items in cart for user: {}", response.size(), email);
    return new ResponseEntity<>(response, HttpStatus.OK);
}
}
