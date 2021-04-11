package ru.voroby.storebackend.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.voroby.storebackend.BootTest;
import ru.voroby.storebackend.model.Product;
import ru.voroby.storebackend.model.Product$;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
public class ProductJpaStreamerTest extends BootTest {

  @Autowired
  private JPAStreamer jpaStreamer;

  @Autowired
  private ProductDAO productDAO;

  @Test
  public void getAll() {
    List<Product> products = jpaStreamer.stream(Product.class).toList();

    assertTrue(products.size() > 0);

    products.forEach(product -> assertNotNull(product.getId()));
  }

  @Test
  public void updateProduct() {
    Product product = jpaStreamer.stream(Product.class)
      .filter(Product$.name.equal("Kayak").and(Product$.category.equal("Watersports")))
      .findFirst().get();
    product.setPrice(300D);
    productDAO.save(product);
    product = productDAO.findByNameAndCategory("Kayak", "Watersports");

    assertEquals(300, product.getPrice());
  }
}
