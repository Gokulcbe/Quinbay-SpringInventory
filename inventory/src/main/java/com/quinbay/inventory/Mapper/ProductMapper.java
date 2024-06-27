package com.quinbay.inventory.Mapper;

import com.quinbay.inventory.dto.ProductDTO;
import com.quinbay.inventory.entity.Product;

public class ProductMapper {
    public static ProductDTO  mapToProductDTO(Product product){
        return new ProductDTO(
                product.getProductName(),
                product.getProductPrice(),
                product.getProductQuantity(),
                product.getCategory(),
                product.getSeller()
        );
    }

}
