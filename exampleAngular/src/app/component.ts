import {Component} from '@angular/core';
import {RepositoryModel} from './repository.model';
import {Product} from './product.model';

@Component({
  selector: 'app-root',
  templateUrl: 'template.html'
})
export class ProductComponent {
  model: RepositoryModel = new RepositoryModel();
  targetName = 'Chess';
  counter = 1;

  constructor() {
    this.onInit();
  }

  getClass(key: number): string {
    const product = this.model.getProduct(key);
    return product.price < 25 ? 'bg-success' : 'bg-warning';
  }

  getClassMap(key: number): object {
    const product = this.model.getProduct(key);
    return {
      'text-center': product != null,
      'bg-info': product.price < 25
    };
  }

  getProduct(key: number): Product {
    return this.model.getProduct(key);
  }

  getProducts(): Product[] {
    return this.model.getProducts();
  }

  getProductCount(): number {
    return this.model.getProducts().length;
  }

  onInit() {
    setInterval(() => { this.counter++; }, 1000);
  }
}
