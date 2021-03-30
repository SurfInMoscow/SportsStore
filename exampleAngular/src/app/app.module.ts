import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {ProductComponent} from './component';
import {FormsModule} from '@angular/forms';

@NgModule({
  // регистрация компонентов, описывет функции для внешнего доступа(других приложений)
  declarations: [
    AppComponent, ProductComponent
  ],
  // зависимости модуля/приложения
  imports: [
    BrowserModule, FormsModule
  ],
  providers: [],
  // точка входа в приложение
  bootstrap: [ProductComponent]
})
export class AppModule { }
