import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth-service';
import { Navigation } from "./components/navigation/navigation";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navigation],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'ngSoilMates';

constructor(
  private auth: AuthService
) {}

ngOnInit() {

}

}

