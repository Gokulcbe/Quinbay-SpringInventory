package com.quinbay.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "Seller")
@Entity
public class SellerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sellerId;
    private String sellerName;
    private String sellerAddress;
    private String sellerContact;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
//    @JsonIgnoreProperties("seller")
    @JsonBackReference
    private List<Product> product;
}
