package com.micorservices.order_service.service;


import com.micorservices.order_service.dto.InventoryResponse;
import com.micorservices.order_service.dto.OrderLineItemsDto;
import com.micorservices.order_service.dto.OrderRequest;
import com.micorservices.order_service.dto.OrderResponse;
import com.micorservices.order_service.event.OrderPlaceEvent;
import com.micorservices.order_service.model.Order;
import com.micorservices.order_service.model.OrderLineItems;
import com.micorservices.order_service.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional

public class OrderService {

    private OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String,String> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineList(orderRequest.orderLineList().stream().map(this::mapOrderLineDtoToOrderLineItem).toList())
                .build();

        List<String> skuCodes = order.getOrderLineList().stream()
                .map(OrderLineItems::getSkuCode) // Fixed typo
                .toList();


        InventoryResponse[] inventoryResponsesArray = webClientBuilder.build().get()
                .uri("http://INVENTORY-SERVICE/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInstock;
        if (inventoryResponsesArray.length == 0) {
            allProductsInstock = false;  // Explicitly handle empty response as 'not in stock'
        } else {
            allProductsInstock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isPresent);

        }



        if (allProductsInstock) {
            orderRepository.save(order);

                kafkaTemplate.send("notificationTopic", order.getOrderNumber());

            return "Order Saved successfully";
        } else {
            throw new IllegalArgumentException("Product is not in stock, try again");
        }


    }

    public OrderLineItems mapOrderLineDtoToOrderLineItem(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .skuCode(orderLineItemsDto.skuCode())
                .quantity(orderLineItemsDto.quantity())
                .price(orderLineItemsDto.price())
                .build();
    }

    public List<OrderResponse> getAllOrderLineItems() {
        return orderRepository.findAll().stream().map(this::mapToOrderResponce).toList();
    }

    private OrderResponse mapToOrderResponce(Order order) {
        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderLineList().stream().map(this::mapToOrderLineDto).toList());
    }

    private OrderLineItemsDto mapToOrderLineDto(OrderLineItems orderLineItems) {
        return new OrderLineItemsDto(orderLineItems.getId(), orderLineItems.getSkuCode(), orderLineItems.getPrice(), orderLineItems.getQuantity());
    }


}

