package com.quinbay.inventory.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private String categoryName;

    public CategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }
}
