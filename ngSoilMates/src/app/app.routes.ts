import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { Login } from './components/login/login';
import { Register } from './components/register/register';
import { ViewPlantSpecies } from './components/view-plant-species/view-plant-species';

export const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home' },
  {path: 'home', component: Home},
  {path: 'register', component: Register},
  {path: 'login', component: Login},
  {path: 'viewPlantSpecies/:speciesId', component: ViewPlantSpecies}
  // {path: '**', component: NotFound}
];
