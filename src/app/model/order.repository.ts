import {Injectable} from '@angular/core';
import {Order} from './order.model';
import {StaticDatasource} from './static.datasource';
import {Observable} from 'rxjs';

@Injectable()
export class OrderRepository {
  private orders: Order[] = [];

  constructor(private dataSource: StaticDatasource) {
  }

  saveOrder(order: Order): Observable<Order> {
    return this.dataSource.saveOrder(order);
  }
}
