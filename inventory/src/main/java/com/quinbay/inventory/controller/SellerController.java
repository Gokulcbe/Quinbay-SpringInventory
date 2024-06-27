package com.quinbay.inventory.controller;

import com.quinbay.inventory.dto.SellerDTO;
import com.quinbay.inventory.entity.SellerDetails;
import com.quinbay.inventory.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService service;

    @PostMapping("/addSeller")
    public SellerDetails postSeller(@RequestBody SellerDetails sellerDetails){
        return service.addSeller(sellerDetails);
    }

    @GetMapping("/getAllSeller")
    public List<SellerDTO> getAllSeller(){
        return service.getAllSeller();
    }

    @GetMapping("/getSeller/{sellerId}")
    public SellerDTO getSeller(@PathVariable long sellerId){
        return service.getSeller(sellerId);
    }
}
