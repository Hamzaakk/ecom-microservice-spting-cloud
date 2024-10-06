package com.micorservices.product_serivce.dto;

import java.math.BigDecimal;

public record ProductResponse(
        String productId,
        String name,
        String description,
        BigDecimal price
) {
}
