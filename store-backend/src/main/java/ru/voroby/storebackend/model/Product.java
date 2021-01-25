package ru.voroby.storebackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

  private String name;

  private String category;

  private String description;

  private Double price;

}
