package com.quinbay.inventory.dto;

import lombok.Data;

@Data
public class SellerDTO {
    private String sellerName;
    private String sellerAddress;

    public SellerDTO(String sellerName, String sellerAddress) {
        this.sellerName = sellerName;
        this.sellerAddress = sellerAddress;
    }
}
