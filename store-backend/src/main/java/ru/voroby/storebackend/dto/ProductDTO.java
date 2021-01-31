package ru.voroby.storebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.voroby.storebackend.model.Product;

@Data
@AllArgsConstructor
public class ProductDTO {

  private Integer id;

  private String name;

  private String category;

  private String description;

  private Double price;

  public static ProductDTO of(Product product) {
    return new ProductDTO(product.getId(),
      product.getName(),
      product.getCategory() == null ? null : product.getCategory(),
      product.getDescription() == null ? null : product.getDescription(),
      product.getPrice());
  }

  public Product toProduct() {
    var product = new Product(name, category, description, price);
    product.setId(id);

    return product;
  }
}
