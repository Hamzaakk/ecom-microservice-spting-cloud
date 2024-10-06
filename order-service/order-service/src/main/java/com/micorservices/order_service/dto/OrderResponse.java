package com.micorservices.order_service.dto;

public record OrderResponse(
        Long id,
        String orderNumber,
        java.util.List<OrderLineItemsDto> orderLineItemsDto
) {
}
