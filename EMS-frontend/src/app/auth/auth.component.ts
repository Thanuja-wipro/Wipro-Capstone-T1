import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  isLoginMode: boolean = true;
  username: string = '';
  password: string = '';
  email: string = '';
  role: string = 'ADMIN';

  constructor(
    private authService: AuthService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
    this.resetFormFields();
  }

  onLogin() {
    this.authService.login(this.email, this.password).subscribe(
      response => {
        if (response.status === 200) {
          this.router.navigate(['/home']);
        }
      },
      error => {
        this.showToast('Login failed. Please try again.');
      }
    );
  }

  onSignup() {
    this.authService.signup(this.username, this.email, this.role, this.password).subscribe(
      response => {
        if (response.status === 200) {
          this.isLoginMode = true;
          this.resetFormFields();
          this.showToast('Signup successful! Please log in.');
        }
      },
      error => {
        this.showToast('Signup failed. Please try again.');
      }
    );
  }

  resetFormFields() {
    this.username = '';
    this.password = '';
    this.email = '';
    this.role = 'ADMIN';
  }

  showToast(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
    });
  }
}
