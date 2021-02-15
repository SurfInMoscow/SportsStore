import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from './product.model';
import {Order} from './order.model';
import {map} from 'rxjs/operators';

const HOSTNAME = 'localhost';
const PORT = 8080;
const PROTOCOL = 'http';

@Injectable()
export class RestDataSource {
  baseUrl: string;
  authToken: string;

  constructor(private http: HttpClient) {
    this.baseUrl = `${PROTOCOL}://${HOSTNAME}:${PORT}/`;
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + 'products');
  }

  saveOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(this.baseUrl + 'orders', order);
  }

  authenticate(user: string, pass: string): Observable<boolean> {
    return this.http.post<any>(this.baseUrl + 'authentication/login', { username: user, password: pass })
      .pipe(map(res => {
        this.authToken = res.success ? res.token : null;

        return res.success;
      }));
  }

  private getOptions() {
    return {
      headers: new HttpHeaders({
        Authorization: `Bearer ${this.authToken}`
      })
    };
  }
}
