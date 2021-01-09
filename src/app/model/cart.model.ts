import {Injectable} from '@angular/core';
import {Product} from './product.model';

@Injectable()
export class Cart {
  public lines: CartLine[] = [];
  public itemCount = 0;
  public cartPrice = 0;

  addLine(product: Product, quantity: number = 1) {
    const line = this.lines.find(cl => cl.product.id === product.id);
    if (line !== undefined) {
      line.quantity += quantity;
    } else {
      this.lines.push(new CartLine(product, quantity));
    }
    this.recalculate();
  }

  updateQuantity(product: Product, quantity: number) {
    const line = this.lines.find(cl => cl.product.id === product.id);
    if (line !== undefined) {
      line.quantity = quantity;
    }
    this.recalculate();
  }

  removeLine(id: number) {
    const line = this.lines.findIndex(cl => cl.product.id === id);
    this.lines.splice(line);
    this.recalculate();
  }

  clear() {
    this.lines = [];
    this.cartPrice = 0;
    this.itemCount = 0;
  }

  private recalculate() {
    this.cartPrice = 0;
    this.itemCount = 0;
    this.lines.forEach(cl => {
      this.itemCount += cl.quantity;
      this.cartPrice += (cl.product.price * cl.quantity);
    });
  }
}

class CartLine {
  constructor(public product: Product,
              public quantity: number) {
  }

  get lineTotal(): number {
    return this.product.price * this.quantity;
  }
}
