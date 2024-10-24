package com.micorservices.order_service.controller;

import com.micorservices.order_service.dto.OrderRequest;
import com.micorservices.order_service.dto.OrderResponse;
import com.micorservices.order_service.model.Order;
import com.micorservices.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethodForPlaceOrder")
    @TimeLimiter(name = "inventory", fallbackMethod = "fallbackMethodForPlaceOrder")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
       return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));
    }

    public  CompletableFuture<String> fallbackMethodForPlaceOrder(OrderRequest orderRequest , RuntimeException e) {
       return CompletableFuture.supplyAsync(()-> "opps try again the service in not available now or product is not on stock"
       );
    }

    @GetMapping
    public List<OrderResponse> getOrder() {
        return orderService.getAllOrderLineItems();
    }
}
