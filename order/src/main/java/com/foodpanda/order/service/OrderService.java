package com.foodpanda.order.service;

import com.foodpanda.order.dto.MenuDto;
import com.foodpanda.order.entity.Order;
import com.foodpanda.order.entity.OrderItem;
import com.foodpanda.order.producer.OrderProducer;
import com.foodpanda.order.repository.OrderItemRepository;
import com.foodpanda.order.repository.OrderRepository;
import com.foodpanda.shared.dto.OrderDto;
import com.foodpanda.shared.dto.OrderItemDto;
import com.foodpanda.shared.enums.OrderStatus;
import com.foodpanda.shared.event.OrderEvent;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private OrderProducer producer;

    @Transactional
    public OrderDto placeOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);

        List<Integer> menuIds = orderDto.getOrderItems().stream()
                .map(OrderItemDto::getMenuId)
                .collect(Collectors.toList());

        String menuEndpoint = UriComponentsBuilder
                .fromUri(URI.create("/restaurants/menu"))
                .queryParam("menuIds", menuIds)
                .build()
                .toString();

        MenuDto[] menuDtos = restTemplate.getForObject(menuEndpoint, MenuDto[].class, Map.of("menuIds", menuIds));
        Map<Integer, MenuDto> menuDtoMap = Arrays.stream(menuDtos).collect(Collectors.toMap(
                MenuDto::getId,
                menuDto -> menuDto,
                (ex, re) -> ex
        ));

        List<OrderItem> orderItems = order.getOrderItems()
                .stream()
                .map(orderItem -> {
                    double menuPrice = menuDtoMap.get(orderItem.getMenuId()).getPrice();
                    orderItem.setPrice(menuPrice);
                    orderItem.setOrder(order);

                    return orderItem;
                }).collect(Collectors.toList());

        order.setOrderItems(orderItems);

        double totalAmount = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();

        order.setTotalAmount(totalAmount);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setPlacedAt(LocalDateTime.now());
        order.setExpectedDeliveryTime(LocalDateTime.now().plusMinutes(30));

        orderRepository.save(order);

        List<OrderItemDto> orderItemDtos = order.getOrderItems().stream().map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class)).collect(Collectors.toList());
        OrderDto orderDto1 = modelMapper.map(order, OrderDto.class);
        orderDto1.setOrderItems(orderItemDtos);

        // send event to kafka
        OrderEvent orderEvent = new OrderEvent(
                order.getId(),
                OrderStatus.PLACED,
                orderDto1
        );

        producer.publish(orderEvent);
        return orderDto1;
    }
}
