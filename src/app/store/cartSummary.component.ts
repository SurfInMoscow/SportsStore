import {Component} from '@angular/core';
import {Cart} from '../model/cart.model';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'cart-summary',
  templateUrl: 'cartSummary.component.html'
})
export class CartSummaryComponent {
  constructor(public cart: Cart) {
  }
}
