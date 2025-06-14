import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  newUser: User = new User();

  constructor(private auth: AuthService, private router: Router){}

  register(user: User): void {
    console.log('Registering user:');
    console.log(user);
    this.auth.register(user).subscribe({
      next: (registeredUser) => {
        this.auth.login(user.username, user.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl('/home');
          },
          error: (problem) => {
            console.error(
              'RegisterComponent.register(): Error logging in user:'
            );
            console.error(problem);
          },
        });
      },
      error: (fail) => {
        console.error(
          'RegisterComponent.register(): Error registering account'
        );
        console.error(fail);
      },
    });
  }

}
