package com.jk.microservices.order.service;

import com.jk.microservices.order.client.InventoryClient;
import com.jk.microservices.order.dto.OrderRequest;
import com.jk.microservices.order.event.OrderPlacedEvent;
import com.jk.microservices.order.model.Order;
import com.jk.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class  OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final InventoryClient inventoryClient;

    @Autowired
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;


    public void placeOrder(OrderRequest orderRequest) {

        boolean isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if(isInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuality(orderRequest.quantity());
            orderRepository.save(order);

            // send message to kafka topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequest.userDetails().email());
            orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
            orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());
            kafkaTemplate.send("order-placed",orderPlacedEvent);
        }else{
            throw new RuntimeException("Product with skucode "+orderRequest.skuCode()+" is not in stack");
        }
    }
}
