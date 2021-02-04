import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';

@Component({
  templateUrl: 'auth.component.html'
})
export class AuthComponent {
  public username: string;
  public password: string;
  public errorMessage: string;

  constructor(public router: Router) {}

  authenticate(form: NgForm) {
    if (form.valid) {
      /*Выполнить авторизацию*/
      this.router.navigateByUrl('/admin/main');
    } else {
      this.errorMessage = 'Invalid form data.';
    }
  }
}
