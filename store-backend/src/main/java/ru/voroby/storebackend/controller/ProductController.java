package ru.voroby.storebackend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voroby.storebackend.model.Product;
import ru.voroby.storebackend.repository.ProductDAO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/products", produces = APPLICATION_JSON_VALUE)
public class ProductController {

  private ProductDAO productDAO;

  public ProductController(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");

    return ResponseEntity.ok().headers(headers).body(StreamSupport
      .stream(productDAO.findAll().spliterator(), false)
      .collect(Collectors.toList()));
  }
}
