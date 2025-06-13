import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth-service';
import { Router } from '@angular/router';
import { User } from '../../models/user';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
loginUser: User = new User();


constructor(
private auth: AuthService,
private router: Router,
){}


login(user: User) {
console. log('Logging in');
console. log(user);

this.auth.login(user.username, user.password).subscribe({
        next: (loggedInUser) => {
          this.router.navigateByUrl('/home');
        },
        error: (problem) => {
          console.error('❌ LoginComponent.login() error logging in user');
          console.error(problem);
        }
      });

  }
}
