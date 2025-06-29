import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { Login } from './components/login/login';
import { Register } from './components/register/register';
import { ViewPlantSpecies } from './components/view-plant-species/view-plant-species';
import { Profile } from './components/profile/profile';
import { ViewUserPlant } from './components/view-user-plant/view-user-plant';
import { NewCareLog } from './components/new-care-log/new-care-log';
import { NotFound } from './components/not-found/not-found';

export const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home' },
  {path: 'home', component: Home},
  {path: 'register', component: Register},
  {path: 'login', component: Login},
  {path: 'viewPlantSpecies/:speciesId', component: ViewPlantSpecies},
  {path: 'profile', component: Profile},
  {path: 'viewUserPlant/:userPlantId', component: ViewUserPlant},
  {path: 'viewUserPlant/:userPlantId/newCareLog', component: NewCareLog},
  {path: 'viewUserPlant/:userPlantId/editCareLog/:careLogId', component: NewCareLog},
  {path: '**', component: NotFound}
];
