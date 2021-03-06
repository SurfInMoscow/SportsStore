package ru.voroby.storebackend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.voroby.storebackend.model.Cart;
import ru.voroby.storebackend.model.CartLine;
import ru.voroby.storebackend.model.Order;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderDAOTest extends AbstractJpaTest {

  @Autowired
  private OrderDAO orderDAO;

  @Autowired
  private ProductDAO productDAO;

  @Test
  public void save() {
    var product = productDAO.findByNameAndCategory("Kayak", "Watersports");
    var cartLine = new CartLine();
    cartLine.setProduct(product);
    cartLine.setQuantity(3);
    var cart = new Cart();
    cart.getCartLines().add(cartLine);
    cart.setItemCount(3);
    cart.setCartPrice(275D);
    cartLine.setCart(cart);
    var order = new Order("My Order", "Lost street", "Moscow",
      "MO", "105275", "Russia", false, cart);
    order.setCart(cart);
    orderDAO.save(order);

    Stream<Order> orders = orderDAO.findAllByName("My Order");
    var myOrder = orders.findFirst().orElse(new Order());

    assertEquals("My Order", myOrder.getName());

    var cartLines = myOrder.getCart().getCartLines();

    assertEquals(1, cartLines.size());

    var cartLine1 = cartLines.iterator().next();
    var cart1 = cartLine1.getCart();

    assertEquals(cart, cart1);
  }

  @Test
  public void getOrder() {
    final var orderStream = orderDAO.findAllByName("TestOrder");
    final var order = orderStream.findFirst().orElse(new Order());

    assertEquals("TestOrder", order.getName());

    final var cart = order.getCart();
    assertEquals(3, cart.getCartPrice());

    final var cartLines = cart.getCartLines();

    assertEquals(1, cartLines.size());
  }

  @Test
  public void update() {
    final var order = orderDAO.findAllByName("TestOrder").findFirst().get();
    var cartLine = Stream.of(order)
      .map(Order::getCart)
      .map(Cart::getCartLines)
      .findFirst().get().iterator().next();
    cartLine.setQuantity(2);
    orderDAO.save(order);
    var updated = orderDAO.findAllByName("TestOrder").findFirst().get();
    var updatedCartLine = Stream.of(updated)
      .map(Order::getCart)
      .map(Cart::getCartLines)
      .findFirst().get().iterator().next();

    assertEquals(cartLine.getQuantity(), updatedCartLine.getQuantity());
  }

  @Test
  public void delete() {
    orderDAO.deleteById(12);
    Optional<Order> byId = orderDAO.findById(12);
    assertTrue(byId.isEmpty());
  }
}
