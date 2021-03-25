import {Product} from './product.model';

export class SimpleDatasource {
  private readonly data: Product[];

  constructor() {
    this.data = new Array<Product>(
      new Product('Socks', 'Clothes', 3.00, 1, undefined, 'Warm socks'),
      new Product('T-Short', 'Clothes', 15.00, 2, undefined, 'Hawaii'),
      new Product('Ball', 'Sport', 20.00, 3, undefined, 'Soccer ball'),
      new Product('Chess', 'Sport', 50.00, 1, undefined, 'For clever'),
      new Product('Glasses', 'Watersport', 30.00, 1, undefined, 'For champions')
    );
  }

  getData(): Product[] {
    return this.data;
  }
}
