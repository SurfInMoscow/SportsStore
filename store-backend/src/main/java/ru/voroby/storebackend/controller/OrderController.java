package ru.voroby.storebackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Transactional(readOnly = true)
@RequestMapping(value = "/orders", produces = APPLICATION_JSON_VALUE)
public class OrderController extends BaseController {

  private OrderDAO orderDAO;

  private ProductDAO productDAO;

  public OrderController(OrderDAO orderDAO, ProductDAO productDAO) {
    this.orderDAO = orderDAO;
    this.productDAO = productDAO;
  }

  @Transactional
  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<?> save(@RequestBody @Valid OrderDTO orderDto) {
    orderDAO.save(dtoToJpaEntity(orderDto));
    return ResponseEntity.accepted().build();
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<OrderDTO>> getAll() {
    return ResponseEntity.ok()
      .body(StreamSupport.stream(orderDAO.findAll().spliterator(), false)
      .map(OrderDTO::of)
      .collect(Collectors.toList()));
  }

  @Transactional
  @PutMapping(consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<OrderDTO> update(@RequestBody @Valid OrderDTO orderDto) {
    return ResponseEntity.ok()
      .body(OrderDTO.of(orderDAO.save(dtoToJpaEntity(orderDto))));
  }

  @Transactional
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ResponseEntity<OrderDTO> delete(@PathVariable Integer id) {
    Optional<Order> byId = orderDAO.findById(id);

    return byId.map(order -> {
      orderDAO.deleteById(id);
      return ResponseEntity.ok()
        .body(OrderDTO.of(order));
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  protected Order dtoToJpaEntity(OrderDTO dto) {
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
