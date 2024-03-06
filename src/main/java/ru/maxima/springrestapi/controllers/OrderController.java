package ru.maxima.springrestapi.controllers;

import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.maxima.springrestapi.Service.OrderService;
import ru.maxima.springrestapi.dto.OrderDTO;
import ru.maxima.springrestapi.exceptions.OrderNotCreateException;
import ru.maxima.springrestapi.exceptions.OrderNotFoundException;
import ru.maxima.springrestapi.exceptions.PersonNotCreatedException;
import ru.maxima.springrestapi.models.Order;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {


    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<OrderDTO> getOrders() {
        List<Order> allOrders = orderService.getAllOrders();
        List<OrderDTO> result = new ArrayList<>();
        allOrders.forEach(e -> {
            result.add(orderService.convertToOrderDTO(e));
        });
        return result;
    }

    @GetMapping("/{id}")
    public OrderDTO get(@PathVariable Long id ) throws OrderNotFoundException {
        return orderService.convertToOrderDTO(orderService.getIdFromOrders(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> crateOrder(@PathVariable("id") Long id , @RequestBody  @Valid OrderDTO orderDTO , BindingResult result){


        checkErrors(result);

        orderService.update(id , orderDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> saveOrders(@RequestBody @Valid OrderDTO orderDTO , BindingResult bindingResult){

        checkErrors(bindingResult);

        Order order = orderService.convertToOrder(orderDTO);
        orderService.saveOrder(order);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }






    public void checkErrors(BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder builder = new StringBuilder();

            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> {
                builder.append(error.getField());
                builder.append("-");
                builder.append(error.getDefaultMessage());
            });
            throw new OrderNotCreateException(builder.toString());
        }
    }

}
