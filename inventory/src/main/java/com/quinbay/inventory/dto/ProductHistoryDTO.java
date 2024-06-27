package com.quinbay.inventory.dto;

import lombok.Data;

@Data
public class ProductHistoryDTO {
    private String oldValue;
    private String newValue;
    private String colName;
    private long productId;
    private String modifiedBy;
    private String date;

    public ProductHistoryDTO(String oldValue, String newValue, String colName, long productId, String modifiedBy, String date) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.colName = colName;
        this.productId = productId;
        this.modifiedBy = modifiedBy;
        this.date = date;
    }
}
