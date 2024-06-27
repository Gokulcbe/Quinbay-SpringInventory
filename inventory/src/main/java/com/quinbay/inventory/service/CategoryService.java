package com.quinbay.inventory.service;

import com.quinbay.inventory.entity.Category;
import com.quinbay.inventory.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo repo;

    public Category addCategory(Category category){
        return repo.save(category);
    }

    public List<Category> getAllCategory(){
        return repo.findAll();
    }

    public Optional<Category> getCategory(long categoryId){
        return repo.findById(categoryId);
    }
}
