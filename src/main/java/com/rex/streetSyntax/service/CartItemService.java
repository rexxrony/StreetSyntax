package com.rex.streetSyntax.service;

import com.rex.streetSyntax.entity.CartItem;
import com.rex.streetSyntax.entity.Product;
import com.rex.streetSyntax.entity.User;
import com.rex.streetSyntax.exceptions.ResourceNotFoundException;
import com.rex.streetSyntax.repository.CartItemRepository;
import com.rex.streetSyntax.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(CartItemService.class);

    // Add Item to Cart for a specific user
    public String addItemToCart(String email, Long prodId, int quantity) {
        logger.info("Adding product with ID: {} to cart for user: {}", prodId, email);

        Product product = productRepository.findById(prodId)
                .orElseThrow(() -> {
                    logger.error("Product not found with ID: {}", prodId);
                    return new ResourceNotFoundException("Product not found with id: " + prodId);
                });

        User user = userService.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", email);
                    return new ResourceNotFoundException("User not found: " + email);
                });

        // Create and save the cart item
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        logger.info("Item added successfully to cart for user: {}", email);
        return "Item added successfully to cart!";
    }

    // Remove Item from Cart by ID for a specific user
    public String deleteItemFromCart(String email, long cartItemId) {
        logger.info("Attempting to delete item with ID: {} from cart for user: {}", cartItemId, email);

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> {
                    logger.error("Cart item not found with ID: {}", cartItemId);
                    return new ResourceNotFoundException("Cart item not found with id:" + cartItemId);
                });

        if (!cartItem.getUser().getEmail().equals(email)) {
            logger.error("Unauthorized attempt to delete item from cart with ID: {}", cartItemId);
            throw new ResourceNotFoundException("Unauthorized access to cart item with ID: " + cartItemId);
        }

        cartItemRepository.delete(cartItem);
        logger.info("Item removed successfully from cart with ID: {}", cartItemId);
        return "Item removed from Cart!";
    }

    // Get all cart items for a specific user
    public List<CartItem> getAllCartItems(String email) {
        logger.info("Fetching all cart items for user: {}", email);

        User user = userService.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", email);
                    return new ResourceNotFoundException("User not found: " + email);
                });

        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        logger.debug("Found {} items in cart for user: {}", cartItems.size(), email);
        return cartItems;
    }

}
