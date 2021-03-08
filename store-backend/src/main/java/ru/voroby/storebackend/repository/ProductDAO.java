package ru.voroby.storebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.voroby.storebackend.model.Product;

public interface ProductDAO extends CrudRepository<Product, Integer> , JpaRepository<Product, Integer> {

  Product findByNameAndCategory(String name, String category);

}
