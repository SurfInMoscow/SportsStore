package ru.voroby.storebackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

  private String name;

  private String address;

  private String city;

  private String state;

  private String zip;

  private String country;

  private Boolean shipped;

}
