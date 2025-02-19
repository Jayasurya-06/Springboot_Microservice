package com.jk.microservices.order.service;

import com.jk.microservices.order.client.InventoryClient;
import com.jk.microservices.order.dto.OrderRequest;
import com.jk.microservices.order.model.Order;
import com.jk.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class  OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final InventoryClient inventoryClient;


    public void placeOrder(OrderRequest orderRequest) {

        boolean isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quality());
        if(isInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuality(orderRequest.quality());
            orderRepository.save(order);
        }else{
            throw new RuntimeException("Product with skucode "+orderRequest.skuCode()+" is not in stack");
        }
    }
}
