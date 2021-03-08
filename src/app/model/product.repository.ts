import {Injectable} from '@angular/core';
import {Product} from './product.model';
import {RestDataSource} from './RestDataSource';

@Injectable()
export class ProductRepository {
  private products: Product[] = [];
  private categories: string[] = [];

  constructor(private dataSource: RestDataSource) {
    dataSource.getProducts().subscribe(data => {
      this.products = data;
      this.categories = data.map(p => p.category)
        .filter((c, index, array) => array.indexOf(c) === index)
        .sort();
    });
  }

  saveProduct(product: Product) {
    if (product.id === null || product.id === 0) {
      this.dataSource.saveProduct(product)
        .subscribe(res => this.products.push(res));
    } else {
      this.dataSource.updateProduct(product)
        .subscribe(res => this.products
          .splice(this.products.findIndex(p => p.id === res.id), 1, res));
    }
  }

  getProducts(category: string = null): Product[] {
    return this.products.filter(p => category == null || p.category === category);
  }

  getProduct(id: number): Product {
    return this.products.find(p => p.id === id);
  }

  deleteProduct(id: number) {
    this.dataSource.deleteProduct(id)
      .subscribe(ps => this.products
        .splice(this.products.findIndex(pfi => pfi.id === id), 1));
  }

  getCategories(): string[] {
    return this.categories;
  }
}
