package com.quinbay.inventory.Mapper;

import com.quinbay.inventory.dto.ProductDTO;
import com.quinbay.inventory.dto.SellerDTO;
import com.quinbay.inventory.entity.Product;
import com.quinbay.inventory.entity.SellerDetails;

public class SellerMapper {
    public static SellerDTO mapToSellerDTO(SellerDetails sellerDetails){
        return new SellerDTO(
                sellerDetails.getSellerName(),
                sellerDetails.getSellerAddress()
        );
    }
}
