package ru.voroby.storebackend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.voroby.storebackend.model.Product;

import javax.transaction.Transactional;

@Transactional
public interface ProductDAO extends CrudRepository<Product, Long> {
}
