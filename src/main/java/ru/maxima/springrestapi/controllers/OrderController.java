package ru.maxima.springrestapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxima.springrestapi.Service.OrderService;
import ru.maxima.springrestapi.models.Order;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {


    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> get(@PathVariable Long id ){
        return orderService.getIdFromOrders(id);
    }


}
