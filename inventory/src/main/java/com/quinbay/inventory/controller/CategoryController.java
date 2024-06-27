package com.quinbay.inventory.controller;

import com.quinbay.inventory.entity.Category;
import com.quinbay.inventory.entity.Product;
import com.quinbay.inventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("/addCategory")
    public Category postCategory(@RequestBody Category category){
        return service.addCategory(category);
    }

    @GetMapping("/getAllCategory")
    public List<Category> getAllCategory(){
        return service.getAllCategory();
    }

    @GetMapping("/getCategory/{categoryId}")
    public Optional<Category> getProduct(@PathVariable long categoryId){
        return service.getCategory(categoryId);
    }
}
