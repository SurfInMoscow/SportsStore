import {Component, Output, EventEmitter, ViewEncapsulation} from '@angular/core';
import {FormControl, FormGroup, NgForm, Validators} from '@angular/forms';
import {Product} from './product.model';
import {RepositoryModel} from './repository.model';
import {ProductComponent} from './component';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'productForm',
  templateUrl: 'productForm.template.html'
})
export class ProductFormComponent {

  formSubmitted = false;

  newProduct: Product = new Product('', '', 0);

  constructor(private productComponent: ProductComponent) {
  }

  // tslint:disable-next-line:no-output-rename
  // @Output('newProduct') newProductEvent = new EventEmitter<Product>();

  // productForm: FormGroup;

  /*constructor(private repositoryModel: RepositoryModel) {
  }*/

  // tslint:disable-next-line:use-lifecycle-interface
  /*ngOnInit(): void {
    this.productForm = new FormGroup({
      name: new FormControl(this.newProduct.name, [
        Validators.required,
        Validators.minLength(2)
      ]),
      description: new FormControl(this.newProduct.description, [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(255)
      ]),
      price: new FormControl(this.newProduct.price, [
        Validators.required,
        Validators.pattern('^\\d{0,10}([.]\\d{0,5}){0,1}$'),
        Validators.minLength(1)
      ])
    });
  }*/

  /*get name() {
    return this.productForm.get('name');
  }

  get description() {
    return this.productForm.get('description');
  }

  get price() {
    return this.productForm.get('price');
  }*/

  /*submitForm(form: NgForm) {
    this.formSubmitted = true;
    console.log(form);
    if (form.valid) {
      console.log(this.newProduct);
      // this.newProductEvent.emit(this.newProduct);
      this.productComponent.addProduct(this.newProduct);
      // this.repo.saveProduct(this.newProduct);
      this.newProduct = new Product('', '', 0);
      // this.productForm.reset();
      this.formSubmitted = false;
      // form.reset();
    }
  }*/
}
