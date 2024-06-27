package com.quinbay.inventory.service;

import com.quinbay.inventory.Mapper.ProductMapper;
import com.quinbay.inventory.dto.ProductDTO;
import com.quinbay.inventory.entity.Product;
import com.quinbay.inventory.entity.ProductHistory;
import com.quinbay.inventory.repository.ProductHistoryRepo;
import com.quinbay.inventory.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repository;

    @Autowired
    private ProductHistoryRepo Historyrepo;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static final String TOPIC = "my_topic";

    public ProductDTO getProduct(long prodId){
        Optional<Product> product =  repository.findById(prodId);
        Product product1 = product.orElseThrow(() -> new RuntimeException("Product not found"));
        ProductDTO productDTO = ProductMapper.mapToProductDTO(product1);
        return productDTO;
    }

    public Product getProductById(long prodId){
        Optional<Product> prod = repository.findById(prodId);
        Product product = prod.get();
        return product;
    }

    @KafkaListener(topics = "order_topic", groupId = "Group_Order")
    public void handleOrderEvent(String message){
        System.out.println("Received Message from Kafka : " + message);
    }

    public List<ProductDTO> getAllProduct(){
        List<Product> products =  repository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product : products){
            productDTOs.add(ProductMapper.mapToProductDTO(product));
        }
        kafkaTemplate.send("GetAll", "Products got");
        return productDTOs;
    }

    public Product postProduct(Product product){
        repository.save(product);
        return product;
    }

    public ProductDTO updateProduct(Product product){
        updateProductHistory(product);
        Product prod = repository.saveAndFlush(product);
        return ProductMapper.mapToProductDTO(prod);
    }

    public ProductDTO updateStock(long prodId, int stock){
        Optional<Product> prod = repository.findById(prodId);
        Product product = prod.get();
        product.setProductQuantity(stock);
        Product updatedProduct = repository.saveAndFlush(product);
        return ProductMapper.mapToProductDTO(updatedProduct);
    }

    public String deleteProduct(long prodId){
        repository.deleteById(prodId);
        return "Product deleted : " + prodId;
    }


    public String updateProductHistory(Product product)
    {
            if (product.getProductName() != null  && product.getProductPrice() > 0 && product.getProductQuantity() > 0) {
                Product oldProduct=getProductById(product.getProductId());
                if(!oldProduct.getProductName().equals(product.getProductName()))
                {
                    ProductHistory productHistory=new ProductHistory();
                    productHistory.setProductId(oldProduct.getProductId());
                    productHistory.setColName("product name");
                    productHistory.setOldValue(oldProduct.getProductName());
                    productHistory.setNewValue(product.getProductName());
                    Historyrepo.save(productHistory);
                }
                if(oldProduct.getProductQuantity()!=product.getProductQuantity())
                {
                    ProductHistory productHistory=new ProductHistory();
                    productHistory.setProductId(oldProduct.getProductId());
                    productHistory.setColName("product quantity");
                    productHistory.setOldValue(oldProduct.getProductQuantity()+"");
                    productHistory.setNewValue(product.getProductQuantity()+"");
//                    productHistory.setDate(currentDate+"");
                    Historyrepo.save(productHistory);
                }
                if(oldProduct.getProductPrice()!=product.getProductPrice())
                {
                    ProductHistory productHistory=new ProductHistory();
                    productHistory.setProductId(oldProduct.getProductId());
                    productHistory.setColName("product cost");
                    productHistory.setOldValue(oldProduct.getProductPrice()+"");
                    productHistory.setNewValue(product.getProductPrice()+"");
//                    productHistory.setDate(currentDate+"");
                    Historyrepo.save(productHistory);

                }

            } else {
                return "invalid input";
            }
            return "History Updated";
        }
}
