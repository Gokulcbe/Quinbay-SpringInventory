package com.quinbay.inventory.repository;

import com.quinbay.inventory.entity.SellerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<SellerDetails, Long> {

}
