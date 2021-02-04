package ru.voroby.storebackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.voroby.storebackend.dto.OrderDTO;
import ru.voroby.storebackend.model.Order;
import ru.voroby.storebackend.repository.OrderDAO;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@RestController
@RequestMapping(value = "/orders", produces = APPLICATION_JSON_VALUE)
public class OrderController {

  private OrderDAO orderDAO;

  public OrderController(OrderDAO orderDAO) {
    this.orderDAO = orderDAO;
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void saveOrder(@Valid @RequestBody OrderDTO orderDto) {
    Order order = orderDto.toOrder();
    orderDAO.save(order);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> exceptionHandle(MethodArgumentNotValidException ex) {
    Map<String, String> map = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String defaultMessage = error.getDefaultMessage();
      map.put(fieldName, defaultMessage);
    });

    return map;
  }

}
