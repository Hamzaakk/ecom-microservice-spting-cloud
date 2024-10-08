package com.micorservices.order_service.controller;

import com.micorservices.order_service.dto.OrderRequest;
import com.micorservices.order_service.dto.OrderResponse;
import com.micorservices.order_service.model.Order;
import com.micorservices.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "order created successfully";
    }

    public String fallbackMethod(OrderRequest orderRequest , RuntimeException e) {
        return "opps try again the service in not available now";
    }

    @GetMapping
    public List<OrderResponse> getOrder() {
        return orderService.getAllOrderLineItems();
    }
}
