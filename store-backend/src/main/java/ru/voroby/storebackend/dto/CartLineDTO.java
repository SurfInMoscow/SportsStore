package ru.voroby.storebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.voroby.storebackend.model.CartLine;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartLineDTO {

  private Integer quantity;

  private ProductDTO product;

  public CartLine toCartLine() {
    return new CartLine(quantity, product.toProduct(), null);
  }

}
