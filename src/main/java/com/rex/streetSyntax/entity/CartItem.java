package com.rex.streetSyntax.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;


    @ManyToOne
    @JoinColumn(name = "prod_Id")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    private int quantity;


}
