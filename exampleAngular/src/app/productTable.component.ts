import {Component, Input} from '@angular/core';
import {RepositoryModel} from './repository.model';
import {Product} from './product.model';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'productTable',
  templateUrl: 'productTable.template.html'
})
export class ProductTableComponent {

  // tslint:disable-next-line:no-input-rename
  @Input('repositoryModel') private repositoryModel: RepositoryModel;

  private showTable = true;

  getProduct(key: number): Product {
    return this.repositoryModel.getProduct(key);
  }

  getProducts(): Product[] {
    return this.repositoryModel.getProducts();
  }

  deleteProduct(key: number) {
    this.repositoryModel.deleteProduct(key);
  }
}
