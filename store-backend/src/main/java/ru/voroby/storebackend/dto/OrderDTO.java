package ru.voroby.storebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.voroby.storebackend.model.Order;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

  private Integer id;

  @NotBlank
  private String name;

  @NotBlank
  private String address;

  @NotBlank
  private String city;

  @NotBlank
  private String state;

  @NotBlank
  private String zip;

  @NotBlank
  private String country;

  private Boolean shipped;

  @NotNull
  private CartDTO cart;

  public Order toOrder() {
    var cart = this.cart.toCart();
    var order = new Order(name, address, city, state, zip, country, shipped, cart);
    order.setId(id);

    return order;
  }

  public static OrderDTO of(Order order) {
    return new OrderDTO(order.getId(), order.getName(), order.getAddress(),
      order.getCity(), order.getState(), order.getZip(), order.getCountry(),
      order.getShipped(), CartDTO.of(order.getCart()));
  }

}
