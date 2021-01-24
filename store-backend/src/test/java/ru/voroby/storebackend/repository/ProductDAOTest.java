package ru.voroby.storebackend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.voroby.storebackend.BootTest;
import ru.voroby.storebackend.model.Product;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class ProductDAOTest extends BootTest {

  @Autowired
  private ProductDAO productDAO;

  @Test
  public void getAll() {
    Iterable<Product> all = productDAO.findAll();
    final var products = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());

  }
}
