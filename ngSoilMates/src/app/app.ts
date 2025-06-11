import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth-service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'ngSoilMates';

constructor(
  private auth: AuthService
) {}

ngOnInit() {
  this.tempTestDeleteMeLater(); // DELETE LATER!!!
}

tempTestDeleteMeLater() {
  this.auth.login('test','test').subscribe({ // change username to match DB
    next: (data) => {
      console.log('Logged in:');
      console.log(data);
    },
    error: (fail) => {
      console.error('Error authenticating:')
      console.error(fail);
    }
  });
}



}
