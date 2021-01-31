package ru.voroby.storebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.voroby.storebackend.model.Order;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

  private Integer id;

  private String name;

  private String address;

  private String city;

  private String state;

  private String zip;

  private String country;

  private Boolean shipped;

  private CartDTO cart;

  public Order toOrder() {
    var cart = this.cart.toCart();
    var order = new Order(name, address, city, state, zip, country, shipped, cart);
    order.setId(id);

    return order;
  }

}
