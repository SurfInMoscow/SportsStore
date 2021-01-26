package ru.voroby.storebackend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.voroby.storebackend.model.Order;

import javax.transaction.Transactional;

@Transactional
public interface OrderDAO extends CrudRepository<Order, Long> {
}
