package com.micorservices.order_service.dto;

import com.micorservices.order_service.model.OrderLineItems;

import java.util.List;

public record OrderRequest(
        List<OrderLineItemsDto> orderLineList
) {
}
