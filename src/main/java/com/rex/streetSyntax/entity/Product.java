package com.rex.streetSyntax.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long prodId;

    private String prodName;
    private String prodDesc;
    private double price;
    private int quantity;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> imageUrls = new ArrayList<>(); // Store multiple image URLs

}
