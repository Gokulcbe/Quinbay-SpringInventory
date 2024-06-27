package com.quinbay.orders.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private int orderId;
    private int cartId;
    private String orderName;
    private int orderQuantity;
    private int orderPrice;

    public OrderDTO(int orderId, int cartId, String orderName, int orderPrice, int orderQuantity) {
        this.orderId = orderId;
        this.cartId = cartId;
        this.orderName = orderName;
        this.orderQuantity= orderQuantity;
        this.orderPrice = orderPrice;
    }
}
