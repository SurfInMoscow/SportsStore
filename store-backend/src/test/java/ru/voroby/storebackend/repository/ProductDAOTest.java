package ru.voroby.storebackend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.voroby.storebackend.BootTest;
import ru.voroby.storebackend.model.Product;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class ProductDAOTest extends BootTest {

  @Autowired
  private ProductDAO productDAO;

  @Test
  public void getAll() {
    Iterable<Product> all = productDAO.findAll();
    final var products = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());

  }

  @Test
  public void updateProduct() {
    var product = productDAO.findByNameAndCategory("Kayak", "Watersports");
    product.setPrice(300D);
    productDAO.save(product);
    product = productDAO.findByNameAndCategory("Kayak", "Watersports");

    assertEquals(300, product.getPrice());
  }
}
