package com.quinbay.orders.Mapper;

import com.quinbay.orders.dto.OrderDTO;
import com.quinbay.orders.entity.Order;

public class OrderMapper {
    public static OrderDTO mapToOrderDTO(Order order){
        return new OrderDTO(
                order.getOrderId(),
                order.getCartId(),
                order.getOrderName(),
                order.getOrderPrice(),
                order.getOrderQuantity()
        );
    }
}
