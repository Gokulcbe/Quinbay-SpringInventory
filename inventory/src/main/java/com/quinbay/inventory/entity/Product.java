package com.quinbay.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Inventory")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String productName;
    private double productPrice;
    private int productQuantity;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
//    @JsonIgnoreProperties("category")
    @JsonBackReference
    private Category category;
//    private CategoryDTO category;

    @ManyToOne
    @JoinColumn(name = "sellerId", nullable = false)
//    @JsonIgnoreProperties("seller")
    private SellerDetails seller;
//    private SellerDTO sellerDTO;

}
