package com.switchfully.eurder_backend.controller;

import com.switchfully.eurder_backend.dto.orderdto.CreateOrderDto;
import com.switchfully.eurder_backend.dto.orderdto.OrderDto;
import com.switchfully.eurder_backend.dto.orderdto.PreviousOrdersDto;
import com.switchfully.eurder_backend.exception.AuthorizationException;
import com.switchfully.eurder_backend.service.AuthorizationService;
import com.switchfully.eurder_backend.service.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final AuthorizationService authorizationService;
    private final OrderService orderService;

    public OrderController(AuthorizationService authorizationService, OrderService orderService) {
        this.authorizationService = authorizationService;
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public OrderDto createOrder(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody CreateOrderDto createOrderDto) {
        if (!authorizationService.isCustomer(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return orderService.createOrder(createOrderDto, authorization);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/{id}", produces = "application/json")
    public OrderDto reOrder(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable Long id) {
        if (!authorizationService.isCustomer(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return orderService.reOrder(id, authorization);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public PreviousOrdersDto getAllOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!authorizationService.isCustomer(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return orderService.getAllOrders(authorization);
    }

}
