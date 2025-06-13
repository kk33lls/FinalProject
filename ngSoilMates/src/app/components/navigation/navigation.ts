import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Logout } from '../logout/logout';
import { AuthService } from '../../services/auth-service';
import { Login } from '../login/login';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-navigation',
  imports: [CommonModule, RouterLink, Logout, Login, NgbModule],
  templateUrl: './navigation.html',
  styleUrls: ['./navigation.css'],
})
export class Navigation {
  isCollapsed: boolean = false;

  login(): boolean {
    return this.auth.checkLogin();
  }
  constructor(private auth: AuthService) {}
}
