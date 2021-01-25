package ru.voroby.storebackend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.voroby.storebackend.model.Order;

public interface OrderDAO extends CrudRepository<Order, Long> {
}
