package ru.maxima.springrestapi.Service;

import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.springrestapi.dto.OrderDTO;
import ru.maxima.springrestapi.dto.PersonDTO;
import ru.maxima.springrestapi.exceptions.OrderNotFoundException;
import ru.maxima.springrestapi.models.Order;
import ru.maxima.springrestapi.models.Person;
import ru.maxima.springrestapi.repositories.OrderRepositories;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepositories repositories;

    private final ModelMapper modelMapper;
    @Autowired
    public OrderService(OrderRepositories repositories, ModelMapper modelMapper) {
        this.repositories = repositories;
        this.modelMapper = modelMapper;
    }

    public List<Order> getAllOrders(){
        return repositories.findAll();
    }

    public Order getIdFromOrders(Long id){
        Optional<Order> getOrdersId = repositories.findById(id);
        return getOrdersId.orElseThrow(OrderNotFoundException::new);
    }



    public void saveOrder(Order order){
        repositories.save(order);
    }

    public void deleteOrder(Long id){
        repositories.deleteById(id);
    }

    public OrderDTO convertToOrderDTO(Order order){
        return modelMapper.map(order , OrderDTO.class);
    }


    public Order convertToOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO , Order.class);
//        enrichData(person);
        return order;
    }

    public void update(Long id , OrderDTO editOrder) {
        Order order = getIdFromOrders(id);
        order.setName(order.getName());
        order.setOwner(order.getOwner());
    }
}
