package com.quinbay.orders.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection= "Order")
public class Order {
    @Id
    private int orderId;
    private int cartId;
    private int productId;
    private String orderName;
    private int orderQuantity;
    private int orderPrice;
}
