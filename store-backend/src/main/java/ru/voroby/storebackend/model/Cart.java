package ru.voroby.storebackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart extends AbstractEntity {

  @OneToMany(
    mappedBy = "cart",
    fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
    orphanRemoval = true
  )
  private Set<CartLine> cartLines;

  @Column(name = "item_count")
  private Integer itemCount;

  @Column(name = "cart_price")
  private Double cartPrice;

  public Cart() {
    this.cartLines = new HashSet<>();
  }
}
