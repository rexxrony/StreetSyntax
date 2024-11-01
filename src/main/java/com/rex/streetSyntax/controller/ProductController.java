package com.rex.streetSyntax.controller;

import com.rex.streetSyntax.entity.Product;
import com.rex.streetSyntax.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    //Add Product
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        logger.info("Attempting to add product");
        String response = productService.addProduct(product);
        logger.debug("Product added successfully!");
        return new ResponseEntity<>(response, HttpStatus.CREATED); //201 CREATED
    }

    //Delete Product By Id

    @DeleteMapping("/delete/{prodId}")
    public ResponseEntity<String> deleteProductById(@PathVariable long prodId){
        logger.info("Attempting to delete product with ID: {}", prodId);
        String response= productService.deleteProductById(prodId);
        logger.debug("Product deleted successfully with ID: {}", prodId);
        return new ResponseEntity<>(response, HttpStatus.OK); //200 OK
    }

    //Get All Products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        logger.info("Fetching all products");
        List<Product> response= productService.getAllProducts();
        logger.debug("Fetched {} products", response.size());
        return new ResponseEntity<>(response,HttpStatus.OK); //200 OK
    }

    //Get Product By ID
    @GetMapping("/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable long prodId){
        logger.info("Fetching product with ID: {}", prodId);
        Product product= productService.getProductById(prodId);
        logger.debug("Product fetched successfully with ID: {}", prodId);
        return new ResponseEntity<>(product,HttpStatus.OK); //200 OK
    }

}
