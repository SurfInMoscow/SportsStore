package ru.voroby.storebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.voroby.storebackend.model.CartLine;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartLineDTO {

  private Integer id;

  private Long version;

  private Integer quantity;

  private ProductDTO product;

  public CartLine toCartLine() {
    CartLine cartLine = new CartLine(quantity, product.toProduct(), null);
    cartLine.setId(id);
    cartLine.setVersion(version);

    return cartLine;
  }

  public static CartLineDTO of(CartLine cartLine) {
    return new CartLineDTO(cartLine.getId(), cartLine.getVersion(),
      cartLine.getQuantity(), ProductDTO.of(cartLine.getProduct()));
  }

}
