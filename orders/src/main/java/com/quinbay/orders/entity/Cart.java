package com.quinbay.orders.entity;

public class Cart {
    private int orderId;
    private int productId;
    private String orderName;
    private int orderQuantity;
    private int orderPrice;

    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int orderId){
        this.orderId = orderId;
    }

    public int getProductId(){
        return productId;
    }

    public void setProductId(int productId){
        this.productId = productId;
    }

    public String getOrderName(){
        return orderName;
    }

    public void setOrderName(String orderName){
        this.orderName = orderName;
    }

    public int getOrderQuantity(){
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity){
        this.orderQuantity = orderQuantity;
    }

    public int getOrderPrice(){
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice){
        this.orderPrice = orderPrice;
    }
}
