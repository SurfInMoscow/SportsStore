import {Injectable} from '@angular/core';
import {Product} from './product.model';
import {from, Observable} from 'rxjs';
import {Order} from './order.model';

@Injectable()
export class StaticDatasource {
  private products: Product[] = [
    new Product(1, 0, 'Product 1', 'Category 1', 'Product 1 (Category 1)', 100),
    new Product(2, 0, 'Product 2', 'Category 1', 'Product 2 (Category 1)', 100),
    new Product(3, 0, 'Product 3', 'Category 1', 'Product 3 (Category 1)', 100),
    new Product(4, 0, 'Product 4', 'Category 1', 'Product 4 (Category 1)', 100),
    new Product(5, 0, 'Product 5', 'Category 1', 'Product 5 (Category 1)', 100),
    new Product(6, 0, 'Product 6', 'Category 2', 'Product 6 (Category 2)', 100),
    new Product(7, 0, 'Product 7', 'Category 2', 'Product 7 (Category 2)', 100),
    new Product(8, 0, 'Product 8', 'Category 2', 'Product 8 (Category 2)', 100),
    new Product(9, 0, 'Product 9', 'Category 2', 'Product 9 (Category 2)', 100),
    new Product(10, 0, 'Product 10', 'Category 2', 'Product 10 (Category 2)', 100),
    new Product(11, 0, 'Product 11', 'Category 3', 'Product 11 (Category 3)', 100),
    new Product(12, 0, 'Product 12', 'Category 3', 'Product 12 (Category 3)', 100),
    new Product(13, 0, 'Product 13', 'Category 3', 'Product 13 (Category 3)', 100),
    new Product(14, 0, 'Product 14', 'Category 3', 'Product 14 (Category 3)', 100),
    new Product(15, 0, 'Product 15', 'Category 3', 'Product 15 (Category 3)', 100)
  ];

  getProducts(): Observable<Product[]> {
    return from([this.products]);
  }

  saveOrder(order: Order): Observable<Order> {
    console.log(JSON.stringify(order));

    return from([order]);
  }
}
