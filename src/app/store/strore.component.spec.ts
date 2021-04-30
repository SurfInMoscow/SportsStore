import 'zone.js/dist/zone-testing';
import {ComponentFixture, TestBed} from '@angular/core/testing';
import {StoreComponent} from './store.component';
import {ProductRepository} from '../model/product.repository';
import {Product} from '../model/product.model';

describe('StoreComponent', () => {

  let fixture: ComponentFixture<StoreComponent>;
  let component: StoreComponent;

  const mockRepository = { getProducts: () => {
      return [
        new Product(1, 0, 'Ball', 'Soccer')
      ]; }
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StoreComponent],
      providers: [
        {provide: ProductRepository, value: mockRepository}
      ]
    });
    fixture = TestBed.createComponent(StoreComponent);
    component = fixture.componentInstance;
  });

  it('StoreComponent defined', () => {
    expect(component).toBeDefined();
  });
});
