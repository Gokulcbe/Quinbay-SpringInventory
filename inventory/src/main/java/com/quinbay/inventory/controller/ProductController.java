package com.quinbay.inventory.controller;

import com.quinbay.inventory.dto.ProductDTO;
import com.quinbay.inventory.entity.Product;
import com.quinbay.inventory.entity.ProductHistory;
import com.quinbay.inventory.service.ProductHistoryService;
import com.quinbay.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductHistoryService historyService;

    @GetMapping("/getProduct/{prodId}")
    public ProductDTO getProduct(@PathVariable long prodId){
        return service.getProduct(prodId);
    }

    @GetMapping(value = "/getAll")
    public List<ProductDTO> getAllProduct(){
        return service.getAllProduct();
    }

    @PostMapping("/postProduct")
    public Product postProduct(@RequestBody Product product){
        return service.postProduct(product);
    }

    @PutMapping("/updateProduct")
    public ProductDTO updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    @PutMapping("/updateStock")
    public ProductDTO updateStock(@RequestBody Product product1){
        ProductDTO product = service.getProduct(product1.getProductId());
//        Product product = prod.get();
        if((product.getProductQuantity()-product1.getProductQuantity())<0){
            System.out.println("Required Stock not available!!!");
            return service.updateStock(product1.getProductQuantity(), 0);
        }
        return service.updateStock(product1.getProductId(),(product.getProductQuantity()-product1.getProductQuantity()));
    }

    @DeleteMapping("/deleteProduct/{prodId}")
    public String deleteProduct(@PathVariable long prodId){
        return service.deleteProduct(prodId);
    }

}
