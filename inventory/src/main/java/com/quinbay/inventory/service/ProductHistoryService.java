package com.quinbay.inventory.service;

import com.quinbay.inventory.Mapper.ProductHistoryMapper;
import com.quinbay.inventory.dto.ProductHistoryDTO;
import com.quinbay.inventory.entity.ProductHistory;
import com.quinbay.inventory.repository.ProductHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductHistoryService {

    @Autowired
    private ProductHistoryRepo repo;

    public ProductHistory postProductHistory(ProductHistory productHistory){
        return repo.save(productHistory);
    }

    public ProductHistoryDTO getProductHistory(long histId){
        Optional<ProductHistory> productHistory =  repo.findById(histId);
        ProductHistory productHistory1 = productHistory.orElseThrow(() -> new RuntimeException("Product not found"));
        ProductHistoryDTO productHistoryDTO = ProductHistoryMapper.mapToProductHistoryDTO(productHistory1);
        return productHistoryDTO;
    }

    public List<ProductHistoryDTO> getAllProductHistory(){
        List<ProductHistory> productHistory =  repo.findAll();
        List<ProductHistoryDTO> productHistoryDTOs = new ArrayList<>();
        for(ProductHistory productHist: productHistory){
            productHistoryDTOs.add(ProductHistoryMapper.mapToProductHistoryDTO(productHist));
        }
        return productHistoryDTOs;
    }
}
