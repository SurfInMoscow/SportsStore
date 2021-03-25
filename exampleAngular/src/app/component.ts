import {Component} from '@angular/core';
import {RepositoryModel} from './repository.model';

@Component({
  selector: 'app-root',
  templateUrl: 'template.html'
})
export class ProductComponent {
  model: RepositoryModel = new RepositoryModel();
}
