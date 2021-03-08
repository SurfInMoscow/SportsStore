package ru.voroby.storebackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.voroby.storebackend.dto.ProductDTO;
import ru.voroby.storebackend.model.Product;
import ru.voroby.storebackend.repository.ProductDAO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Transactional(readOnly = true)
@RequestMapping(value = "/products", produces = APPLICATION_JSON_VALUE)
public class ProductController extends BaseController {

  private ProductDAO productDAO;

  public ProductController(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    final var products = StreamSupport
      .stream(productDAO.findAll().spliterator(), false)
      .collect(Collectors.toList());

    return ResponseEntity.ok()
      .body(products.stream().map(ProductDTO::of).collect(Collectors.toList()));
  }

  @Transactional
  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductDTO productDTO) {
    return ResponseEntity.accepted()
      .body(ProductDTO.of(productDAO.saveAndFlush(productDTO.toProduct())));
  }

  @Transactional
  @PutMapping(consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductDTO> update(@RequestBody @Valid ProductDTO productDTO) {
    return ResponseEntity.accepted()
      .body(ProductDTO.of(productDAO.saveAndFlush(productDTO.toProduct())));
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<ProductDTO> delete(@PathVariable Integer id) {
    Optional<Product> byId = productDAO.findById(id);

    return byId.map(product -> {
      productDAO.deleteById(id);
      return ResponseEntity.ok().body(ProductDTO.of(product));
    })
      .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
