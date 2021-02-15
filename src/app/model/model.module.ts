import {NgModule} from '@angular/core';
import {ProductRepository} from './product.repository';
import {StaticDatasource} from './static.datasource';
import {Cart} from './cart.model';
import {Order} from './order.model';
import {OrderRepository} from './order.repository';
import {RestDataSource} from './RestDataSource';
import {HttpClientModule} from '@angular/common/http';
import {AuthService} from './auth.service';

@NgModule({
  imports: [HttpClientModule],
  providers: [ProductRepository, StaticDatasource, Cart, Order, OrderRepository,
    {provide: StaticDatasource, useClass: RestDataSource}, RestDataSource, AuthService]
})
export class ModelModule {
}
