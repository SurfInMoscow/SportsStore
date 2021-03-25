export class Product {
  constructor(public name: string,
              public category: string,
              public price: number,
              public id?: number,
              public version?: number,
              public description?: string) {
  }
}
