package com.switchfully.eurder_backend.service;

import com.switchfully.eurder_backend.dto.itemgroupdto.CreateItemGroupDto;
import com.switchfully.eurder_backend.dto.orderdto.CreateOrderDto;
import com.switchfully.eurder_backend.dto.orderdto.OrderDto;
import com.switchfully.eurder_backend.dto.orderdto.PreviousOrderDto;
import com.switchfully.eurder_backend.dto.orderdto.PreviousOrdersDto;
import com.switchfully.eurder_backend.entity.ItemGroup;
import com.switchfully.eurder_backend.entity.Order;
import com.switchfully.eurder_backend.entity.User;
import com.switchfully.eurder_backend.exception.AuthorizationException;
import com.switchfully.eurder_backend.exception.NotFoundException;
import com.switchfully.eurder_backend.mapper.OrderMapper;
import com.switchfully.eurder_backend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AuthorizationService authorizationService;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, AuthorizationService authorizationService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.authorizationService = authorizationService;
        this.orderMapper = orderMapper;
    }

    public OrderDto createOrder(CreateOrderDto createOrderDto, String authorization) {
        User user = authorizationService.getUser(authorization);
        Order order = orderMapper.mapCreateOrderDtoToOrder(createOrderDto, user);
        return orderMapper.mapOrderToOrderDto(orderRepository.save(order), user);
    }

    public OrderDto reOrder(Long id, String authorization) {
        User user = authorizationService.getUser(authorization);
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));

        if (order.getUser() != user.getUserId()) {
            throw new AuthorizationException("You are forbidden to access this.");
        }

        List<CreateItemGroupDto> createItemGroupDtoList = new ArrayList<>();

        for (ItemGroup itemGroup : order.getItemGroups()) {
            Long itemId = itemGroup.getItem().getItemId();
            int amount = itemGroup.getAmount();
            createItemGroupDtoList.add(new CreateItemGroupDto(itemId, amount));
        }

        CreateOrderDto createOrderDto = new CreateOrderDto();
        createOrderDto.setCreateItemGroupDtoList(createItemGroupDtoList);

        return createOrder(createOrderDto, authorization);
    }

    public PreviousOrdersDto getAllOrders(String authorization) {
        User user = authorizationService.getUser(authorization);
        List<Order> orders = orderRepository.findByUser(user.getUserId());

        List<PreviousOrderDto> previousOrderDtoList = orders
                .stream()
                .map(orderMapper::mapOrderToPreviousOrderDto)
                .toList();

        double totalPrice = orders
                .stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();

        return orderMapper.mapToPreviousOrdersDto(previousOrderDtoList, totalPrice);
    }

}
