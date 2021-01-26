package ru.voroby.storebackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.voroby.storebackend.model.Order;
import ru.voroby.storebackend.repository.OrderDAO;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/orders", produces = APPLICATION_JSON_VALUE)
public class OrderController {

  private OrderDAO orderDAO;

  public OrderController(OrderDAO orderDAO) {
    this.orderDAO = orderDAO;
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void saveOrder(@Valid @RequestBody Order order) {
    orderDAO.save(order);
  }
}
