package ru.maxima.springrestapi.repositories;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.maxima.springrestapi.models.Order;
import ru.maxima.springrestapi.models.Person;


import java.util.List;

public interface OrderRepositories extends JpaRepository<Order, Long> {


    List<Order> findByName(String itemName);

    List<Order> findByOwner(Person person);

}
