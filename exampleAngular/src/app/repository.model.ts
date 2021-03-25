import {SimpleDatasource} from './datasource.model';
import {Product} from './product.model';

export class RepositoryModel {
  private readonly datasource: SimpleDatasource;
  private readonly products: Product[];
  private locator = (p: Product, id: number) => p.id === id;

  constructor() {
    this.datasource = new SimpleDatasource();
    this.products = new Array<Product>();
    this.datasource.getData()
      .forEach(product => this.products.push(product));
  }

  getProducts(): Product[] {
    return this.products;
  }

  getProduct(id: number): Product {
    // @ts-ignore
    return this.products.find(p => this.locator(p.id, id));
  }

  // tslint:disable-next-line:typedef
  saveProduct(product: Product) {
    if (product.id === 0 || product.id === null) {
      product.id = this.generateID();
      this.products.push(product);
    } else {
      this.products.splice(this.products.findIndex(p => p.id === product.id), 1, product);
    }
  }

  deleteProduct(id: number) {
    const index = this.products.findIndex(p => this.locator(p, id));
    if (index > -1) {
      this.products.splice(index, 1);
    }
  }

  private generateID(): number {
    let num = 100;
    while (this.getProduct(num) !== null) {
      num++;
    }
    return num;
  }
}
