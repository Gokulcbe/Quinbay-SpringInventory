package com.quinbay.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "History")
@Entity
public class ProductHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long historyId;
    private String oldValue;
    private String newValue;
    private String colName;
    private long productId;
    private String modifiedBy;
    private String date;


}
