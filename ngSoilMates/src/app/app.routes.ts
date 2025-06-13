import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { Login } from './components/login/login';
import { Register } from './components/register/register';

export const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home' },
  {path: 'home', component: Home},
  {path: 'register', component: Register},
  {path: 'login', component: Login},
  // {path: '**', component: NotFound}
];
