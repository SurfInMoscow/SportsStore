import {Injectable} from '@angular/core';
import {Cart} from './cart.model';

@Injectable()
export class Order {
  id: number;
  version: number;
  name: string;
  address: string;
  city: string;
  state: string;
  zip: string;
  country: string;
  shipped = false;

  constructor(public cart: Cart) {
  }

  clear() {
    this.id = null;
    this.version = null;
    this.name = this.address = this.city = null;
    this.state = this.zip = this.country = null;
    this.shipped = false;
    this.cart.clear();
  }
}
