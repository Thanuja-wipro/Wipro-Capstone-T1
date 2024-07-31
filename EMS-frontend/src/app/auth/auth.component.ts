import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css'],
})
export class AuthComponent {
  isLoginMode: boolean = true;
  name: string = '';
  password: string = '';
  email: string = '';
  role: string = 'ADMIN';

  constructor(
    private authService: AuthService,
    private snackBar: MatSnackBar,
    private router: Router,
    private userService: UserService
  ) {}

  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
    this.resetFormFields();
  }

  onLogin() {
    if (this.email === '' || this.email===null){
      this.showToast('Please enter the email')
    }else if (this.password === '' || this.password===null){
      this.showToast('Please enter the password')
    }else if (this.email.endsWith('@gmail.com') === false){
      this.showToast('Please enter valid email')
    }else {
      this.authService.login(this.email, this.password).subscribe(
        response => {
          if (response.status === 200) {
            this.userService.setUser(response);
            this.router.navigate(['/home']);
          } 
        },
        error => {
          this.showToast('Login failed. Please try again.');
        }
      );
    }
    
  }

  onSignup() {
    if (this.email === '' || this.email===null){
      this.showToast('Please enter the email')
    }else if (this.password === '' || this.password===null){
      this.showToast('Please enter the password')
    }else if (this.email.endsWith('@gmail.com') === false){
      this.showToast('Please enter valid email')
    } else if (this.name === '' || this.name===null){
      this.showToast('Please enter the name')
    } else if (this.password.length < 8){
      this.showToast('Please enter a minimum of 8 characters')
    }else {
      this.authService.signup(this.name, this.email, this.role, this.password).subscribe(
        response => {
          if (response.status === 200) {
            this.isLoginMode = true;
            this.resetFormFields();
            this.showToast('Signup successful! Please log in.');
          } 
        },
        error => {
          this.showToast('Email is already taken');
        }
      );
    }
    
  }

  resetFormFields() {
    this.name = '';
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
