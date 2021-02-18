package ru.voroby.storebackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.voroby.storebackend.dto.OrderDTO;
import ru.voroby.storebackend.model.Cart;
import ru.voroby.storebackend.model.CartLine;
import ru.voroby.storebackend.model.Order;
import ru.voroby.storebackend.model.Product;
import ru.voroby.storebackend.repository.OrderDAO;
import ru.voroby.storebackend.repository.ProductDAO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@RestController
@RequestMapping(value = "/orders", produces = APPLICATION_JSON_VALUE)
public class OrderController {

  private OrderDAO orderDAO;

  private ProductDAO productDAO;

  public OrderController(OrderDAO orderDAO, ProductDAO productDAO) {
    this.orderDAO = orderDAO;
    this.productDAO = productDAO;
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void save(@RequestBody @Valid OrderDTO orderDto) {
    orderDAO.save(dtoToJpaEntity(orderDto));
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<OrderDTO> getAll() {
    return StreamSupport.stream(orderDAO.findAll().spliterator(), false)
      .map(OrderDTO::of)
      .collect(Collectors.toList());
  }

  //TODO тесты
  @PutMapping(consumes = APPLICATION_JSON_VALUE)
  public OrderDTO update(@RequestBody @Valid OrderDTO orderDTO) {
    return OrderDTO.of(orderDAO.save(dtoToJpaEntity(orderDTO)));
  }

  //TODO тесты
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable Long id) {
    orderDAO.deleteById(id);
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

  private Order dtoToJpaEntity(OrderDTO dto) {
    Order order = dto.toOrder();
    Cart cart = order.getCart();
    List<CartLine> cartLines = new ArrayList<>(cart.getCartLines());

    List<Integer> ids = cartLines
      .stream().map(CartLine::getProduct).map(Product::getId).collect(Collectors.toList());
    List<Product> allById = StreamSupport.stream(productDAO.findAllById(ids).spliterator(), false).collect(Collectors.toList());

    if (ids.size() == allById.size()) {
      for (int i = 0; i < cartLines.size(); i++) {
        CartLine cartLine = cartLines.get(i);
        cartLine.setProduct(allById.get(i));
        cartLine.setCart(cart);
      }
      cart.setCartLines(new HashSet<>(cartLines));
    } else {
      throw new IllegalStateException("Products not found in database.");
    }

    return order;
  }

}
