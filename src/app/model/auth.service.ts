import {Injectable} from '@angular/core';
import {RestDataSource} from './RestDataSource';
import {Observable} from 'rxjs';

@Injectable()
export class AuthService {
  constructor(private restDataSource: RestDataSource) {
  }

  authenticate(user: string, pass: string): Observable<boolean> {
    return this.restDataSource.authenticate(user, pass);
  }

  get authenticated(): boolean {
    return this.restDataSource.authToken !== null;
  }

  clear() {
    this.restDataSource.authToken = null;
  }
}
