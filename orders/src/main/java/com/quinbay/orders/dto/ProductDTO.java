package com.quinbay.orders.dto;

import com.quinbay.orders.entity.Category;
import lombok.Data;

@Data
public class ProductDTO {
    private String productName;
    private double productPrice;
    private int productQuantity;
    private Category category;

    public ProductDTO(String productName, double productPrice, int productQuantity, Category category) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.category = category;
    }
}
