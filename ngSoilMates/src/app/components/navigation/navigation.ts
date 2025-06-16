import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Logout } from '../logout/logout';
import { AuthService } from '../../services/auth-service';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../models/user';
@Component({
  selector: 'app-navigation',
  imports: [CommonModule, RouterLink, Logout, NgbModule],
  templateUrl: './navigation.html',
  styleUrls: ['./navigation.css'],
})
export class Navigation {
  isCollapsed: boolean = false;
  loggedInUser: User = new User();

  constructor(private auth: AuthService, private router: Router) {}

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

}
