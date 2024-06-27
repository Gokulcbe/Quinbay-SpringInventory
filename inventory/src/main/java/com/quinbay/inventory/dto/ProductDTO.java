package com.quinbay.inventory.dto;

import com.quinbay.inventory.Mapper.SellerMapper;
import com.quinbay.inventory.entity.Category;
import com.quinbay.inventory.entity.SellerDetails;
import lombok.Data;

@Data
public class ProductDTO {
    private String productName;
    private double productPrice;
    private int productQuantity;
    private Category category;
    private SellerDTO sellerDTO;


    public ProductDTO(String productName, double productPrice, int productQuantity, Category category, SellerDetails sellerDetails) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.category = category;
        SellerDTO sellerDTO1 = SellerMapper.mapToSellerDTO(sellerDetails);
        this.sellerDTO = sellerDTO1;
    }
}
