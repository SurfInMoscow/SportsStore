import {Component} from '@angular/core';
import {Product} from '../model/product.model';
import {ProductRepository} from '../model/product.repository';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForm} from '@angular/forms';

@Component({
  templateUrl: 'productEditor.component.html'
})
export class ProductEditorComponent {
  editing = false;
  product: Product = new Product();

  constructor(private repository: ProductRepository,
              private router: Router, activatedRoute:
                ActivatedRoute) {
    this.editing = activatedRoute.snapshot.params.mode === 'edit';
    if (this.editing) {
      Object.assign(this.product,
        repository.getProduct(Number(activatedRoute.snapshot.params.id)));
    }
  }

  save(form: NgForm) {
    this.repository.saveProduct(this.product);
    this.router.navigateByUrl('/admin/main/products');
  }
}
