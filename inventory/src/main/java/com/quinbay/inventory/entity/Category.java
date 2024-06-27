package com.quinbay.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "Category")
@Entity
public class Category {
    @Id
    private long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = false)
//    @JsonIgnoreProperties("category")
    @JsonManagedReference
    private List<Product> products;
}
