package com.quinbay.inventory.repository;

import com.quinbay.inventory.entity.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepo extends JpaRepository<ProductHistory, Long> {
}
