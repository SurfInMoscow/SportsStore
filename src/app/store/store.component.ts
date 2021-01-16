import {Component} from '@angular/core';
import {ProductRepository} from '../model/product.repository';
import {Product} from '../model/product.model';
import {Cart} from '../model/cart.model';
import {Router} from '@angular/router';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'store',
  templateUrl: 'store.component.html'
})
export class StoreComponent {
  public selectedCategory: string = null;
  public productsPerPage = 4;
  public selectedPage = 1;

  constructor(private repository: ProductRepository,
              private cart: Cart,
              private router: Router) {
  }

  get products(): Product[] {
    const pageIndex = (this.selectedPage - 1) * this.productsPerPage;
    return this.repository.getProducts(this.selectedCategory)
      .slice(pageIndex, pageIndex + this.productsPerPage);
  }

  get categories(): string[] {
    return this.repository.getCategories();
  }

  changeCategory(newCategory?: string) {
    this.selectedCategory = newCategory;
  }

  changePage(newPage: number) {
    this.selectedPage = newPage;
  }

  changePageSize(newSize: number) {
    this.productsPerPage = newSize;
    this.changePage(1);
  }

  /*getPageNumbers(): number[] {
    const nums = Math.ceil(this.repository.getProducts(this.selectedCategory).length / this.productsPerPage);
    const arr: number[] = [];

    for (let i = 1; i <= nums; i++) {
      arr.push(i);
    }

    return arr;
  }*/

  pageCount(): number {
    return Math.ceil(this.repository.getProducts(this.selectedCategory).length / this.productsPerPage);
  }

  addProductToCart(product: Product) {
    this.cart.addLine(product);
    this.router.navigateByUrl('/cart');
  }
}
