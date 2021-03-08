package ru.voroby.storebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.voroby.storebackend.model.Order;

import java.util.stream.Stream;

public interface OrderDAO extends CrudRepository<Order, Integer>, JpaRepository<Order, Integer> {

  @Query("select o from Order o left join fetch o.cart where o.name = :name")
  Stream<Order> findAllByName(String name);

}
