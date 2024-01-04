package com.bijanghanei.orderservice.service;

import com.bijanghanei.orderservice.dto.InventoryResponse;
import com.bijanghanei.orderservice.dto.OrderLineItemsRequest;
import com.bijanghanei.orderservice.dto.OrderRequest;
import com.bijanghanei.orderservice.model.Order;
import com.bijanghanei.orderservice.model.OrderLineItems;
import com.bijanghanei.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsListRequest()
                        .stream().map(orderLineItemsRequest -> mapToOrderLineItems(orderLineItemsRequest)).toList();
        order.setOrderLineItemsList(orderLineItemsList);
        order.setOrderNumber(UUID.randomUUID().toString());

        //Making a list of skuCodes
        List<String> skuCodes = order.getOrderLineItemsList()
                .stream().map(orderLineItems -> orderLineItems.getSkuCode()).toList();

//        Call inventory-service from order service
        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build()
                ).retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean result = Arrays.stream(inventoryResponseArray).allMatch(inventoryResponse -> inventoryResponse.isInStock());

        if (result){
            orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("We out of stock");
        }

    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsRequest items) {

        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setQuantity(items.getQuantity());
        orderLineItems.setPrice(items.getPrice());
        orderLineItems.setSkuCode(items.getSkuCode());

        return orderLineItems;
    }
}
