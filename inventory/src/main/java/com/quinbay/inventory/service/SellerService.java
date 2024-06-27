package com.quinbay.inventory.service;

import com.quinbay.inventory.Mapper.SellerMapper;
import com.quinbay.inventory.dto.SellerDTO;
import com.quinbay.inventory.entity.SellerDetails;
import com.quinbay.inventory.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    private SellerRepo repo;

    public SellerDetails addSeller(SellerDetails seller){
        return repo.save(seller);
    }

    public List<SellerDTO> getAllSeller(){
        List<SellerDetails> sellers =  repo.findAll();
        List<SellerDTO> sellerDTOs = new ArrayList<>();
        for(SellerDetails seller : sellers){
            sellerDTOs.add(SellerMapper.mapToSellerDTO(seller));
        }
        return sellerDTOs;
    }

    public SellerDTO getSeller(long sellerId){
        Optional<SellerDetails> seller =  repo.findById(sellerId);
        SellerDetails seller1 = seller.orElseThrow(() -> new RuntimeException("Product not found"));
        SellerDTO sellerDTO = SellerMapper.mapToSellerDTO(seller1);
        return sellerDTO;
    }
}
