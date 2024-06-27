package com.quinbay.orders.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quinbay.orders.dto.OrderDTO;
import com.quinbay.orders.dto.ProductDTO;
import com.quinbay.orders.entity.Cart;
import com.quinbay.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/redis")
    public String redisCache(@RequestParam String key, @RequestParam String value){
        return service.redisCache(key, value);
    }


    @GetMapping("/getAll")
    public List<OrderDTO> getAll() throws JsonProcessingException {
        return service.getAllOrders();
    }

    @GetMapping("/getOrder/{orderId}")
    public OrderDTO getOrder(@PathVariable int orderId) throws JsonProcessingException {
        return service.getOrder(orderId);
    }

    @GetMapping("/getProduct/{prodId}")
    public ProductDTO getproduct(@PathVariable long prodId){
        return service.getProductDetails(prodId);
    }

    @PostMapping("/addToCart")
    public ArrayList<Cart> addToCart(@RequestBody Cart cart){
        return service.addToCart(cart);
    }

    @PostMapping("/placeOrder")
    public List<OrderDTO> placeOrder() throws JsonProcessingException {
        return service.placeOrder();
    }

    @DeleteMapping("deleteOrder/{orderId}")
    public String deleteCart(@PathVariable int orderId){
        return service.deleteCart(orderId);
    }

    @GetMapping("/getCart/{cartId}")
    public List<OrderDTO> getCart(@PathVariable int cartId) throws JsonProcessingException {
        return service.getCart(cartId);
    }

}
