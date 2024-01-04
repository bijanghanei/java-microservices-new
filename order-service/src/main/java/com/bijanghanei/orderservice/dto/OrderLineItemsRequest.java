package com.bijanghanei.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsRequest {
    private BigDecimal price;
    private String skuCode;
    private Integer quantity;
}
