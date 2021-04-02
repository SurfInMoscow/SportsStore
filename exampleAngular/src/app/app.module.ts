import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ProductComponent} from './component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ProductTableComponent} from './productTable.component';
import {ProductFormComponent} from './productForm.component';
import {RepositoryModel} from './repository.model';

@NgModule({
  // регистрация компонентов, описывет функции для внешнего доступа(других приложений)
  declarations: [
    ProductComponent, ProductTableComponent, ProductFormComponent
  ],
  // зависимости модуля/приложения
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule
  ],
  providers: [],
  // точка входа в приложение
  bootstrap: [ProductComponent]
})
export class AppModule { }
