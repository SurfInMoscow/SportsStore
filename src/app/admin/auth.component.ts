import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {AuthService} from '../model/auth.service';

@Component({
  templateUrl: 'auth.component.html'
})
export class AuthComponent {
  public username: string;
  public password: string;
  public errorMessage: string;

  constructor(public router: Router, private auth: AuthService) {}

  authenticate(form: NgForm) {
    if (form.valid) {
      this.auth.authenticate(this.username, this.password).subscribe(res => {
        if (res) {
          this.router.navigateByUrl('/admin/main');
        } else {
          this.errorMessage = 'Authentication failed.';
        }
      });
    } else {
      this.errorMessage = 'Invalid form data.';
    }
  }
}
