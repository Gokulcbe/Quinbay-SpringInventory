package com.quinbay.orders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quinbay.orders.Mapper.OrderMapper;
import com.quinbay.orders.dto.OrderDTO;
import com.quinbay.orders.dto.ProductDTO;
import com.quinbay.orders.entity.Cart;
import com.quinbay.orders.entity.Order;
import com.quinbay.orders.entity.Product;
import com.quinbay.orders.repository.OrderRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class OrderService {

    ArrayList<Cart> cartList = new ArrayList<Cart>();
    int cartId = 1;

    @Autowired
    private KafkaTemplate kafkaTemplate;

//    @Override
    @Cacheable(value = "springBootTraining", key="#key")
    public String redisCache(String key, String value) {
        System.out.println("key : " + key + " ,value : " + value);
        return value;
    }

    @PostConstruct
    public void initialize(){
        List<Order> order = repo.findAll();
        for(Order ord : order){
            cartId = Math.max(cartId, ord.getCartId());
        }
        cartId++;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepo repo;

    public List<OrderDTO> getAllOrders() throws JsonProcessingException {
        List<Order> orders =  repo.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for(Order order : orders){
            orderDTOs.add(OrderMapper.mapToOrderDTO(order));
        }
        return orderDTOs;
    }

    public OrderDTO getOrder(int orderId) throws JsonProcessingException {
        Optional<Order> order =  repo.findById(orderId);
        Order order1 = order.orElseThrow(() -> new RuntimeException("Product not found"));
        OrderDTO orderDTO = OrderMapper.mapToOrderDTO(order1);
        String message = "Order Got for ID : " + orderId;
        kafkaTemplate.send("order_topic", message);
        return orderDTO;
    }

    public ArrayList<Cart> addToCart(Cart cart){
        cartList.add(cart);
        String message = "Order added to cart : " + cart.getOrderId();
        kafkaTemplate.send("order_topic", message);
        return cartList;
    }

    public List<OrderDTO> placeOrder() throws JsonProcessingException {
        for(Cart cart : cartList){
            ProductDTO product = getProductDetails(cart.getProductId());
            Order order = new Order();
            order.setOrderId(cart.getOrderId());
            order.setCartId(cartId);
            order.setProductId(cart.getProductId());
            order.setOrderName(cart.getOrderName());
            order.setOrderPrice((int)product.getProductPrice()*cart.getOrderQuantity());
            order.setOrderQuantity(cart.getOrderQuantity());
            repo.save(order);
            updateStock(cart.getProductId(),cart.getOrderQuantity());
        }
        cartList = new ArrayList<>();
        cartId++;
        return getAllOrders();
    }

    public void updateStock(int prodId, int order_quantity){
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.put("Authorization",  new ArrayList<>());
//        HttpEntity<String> entity = new HttpEntity<>(headers);
        Product product = new Product();
        product.setProductId(prodId);
        product.setProductQuantity(order_quantity);
        HttpEntity<Product> entity = new HttpEntity<>(product, headers);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 1001);
        ProductDTO response = restTemplate.
                exchange("http://localhost:8081/product/updateStock" , HttpMethod.PUT, entity, ProductDTO.class, paramMap).getBody();
    }

    public String deleteCart(int orderId){
        repo.deleteById(orderId);
        return "Deleted from Cart";
    }

    public List<OrderDTO> getCart(int cartId) throws JsonProcessingException {
        List<Order> cart = repo.findByCartId(cartId);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for(Order order : cart){
            orderDTOs.add(OrderMapper.mapToOrderDTO(order));
        }
        return orderDTOs;
    }

    public ProductDTO getProductDetails(long prodId){
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.put("Authorization",  new ArrayList<>());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 1001);
        ProductDTO response = restTemplate.
                exchange("http://localhost:8081/product/getProduct/" + prodId, HttpMethod.GET, entity, ProductDTO.class, paramMap).getBody();
        return response;
    }


}
