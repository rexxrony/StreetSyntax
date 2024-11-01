package com.rex.streetSyntax.service;

import com.rex.streetSyntax.entity.Product;
import com.rex.streetSyntax.exceptions.ResourceNotFoundException;
import com.rex.streetSyntax.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    // Add a product
    public String addProduct(Product product) {
        logger.info("Adding product");
        productRepository.save(product);
        logger.info("Product added successfully");
        return "Product added Successfully!";
    }

    // Get all products
    public List<Product> getAllProducts() {
        logger.info("Fetching all products from the repository");
        List<Product> products = productRepository.findAll();
        logger.debug("Fetched {} products", products.size());
        return products;

    }

    // Get product by ID
    public Product getProductById(long prodId) {
        logger.info("Searching for product with ID: {}", prodId);
        return productRepository.findById(prodId)
                .orElseThrow(() -> {
                    logger.error("Product not found with ID: {}", prodId);
                    return new ResourceNotFoundException("Product not found with id " + prodId);
                });

    }

    // Delete product by ID //check
    public String deleteProductById(long prodId) {
        logger.info("Attempting to delete product with ID: {}", prodId);
        Product product = productRepository.findById(prodId)
                .orElseThrow(() -> {
                    logger.error("Product not found with ID: {}", prodId);
                    return new ResourceNotFoundException("Product not found with id: " + prodId);
                });

        productRepository.delete(product);
        logger.info("Product deleted successfully with ID: {}", prodId);
        //productRepository.deleteById(prodId);
        return "Product removed successfully!";
    }
}
