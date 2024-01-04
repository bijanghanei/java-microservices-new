package com.bijanghanei.orderservice.controller;

import com.bijanghanei.orderservice.dto.OrderRequest;
import com.bijanghanei.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
    }
}
