package com.bijanghanei.orderservice.dto;

import com.bijanghanei.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String orderNumber;
    private List<OrderLineItemsRequest> orderLineItemsListRequest;
}
