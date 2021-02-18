package ru.voroby.storebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.voroby.storebackend.model.Cart;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

  private Set<CartLineDTO> lines;

  private Integer itemCount;

  private Double cartPrice;

  public Cart toCart() {
    var cartLines = lines.stream().map(CartLineDTO::toCartLine).collect(Collectors.toSet());
    return new Cart(cartLines, itemCount, cartPrice);
  }

  public static CartDTO of(Cart cart) {
    return new CartDTO(cart.getCartLines().stream()
      .map(CartLineDTO::of)
      .collect(Collectors.toSet()),
      cart.getItemCount(),
      cart.getCartPrice());
  }

}
