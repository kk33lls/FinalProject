import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth-service';
import { environment } from '../../environments/environment';
import { catchError, Observable, throwError } from 'rxjs';
import { UserPlant } from '../models/user-plant';
import { PlantSpecies } from '../models/plant-species';
import { observableToBeFn } from 'rxjs/internal/testing/TestScheduler';

@Injectable({
  providedIn: 'root'
})
export class UserPlantsService {
  private url = environment.baseUrl + 'api/plants';

  constructor(private http: HttpClient, private auth: AuthService) { }

  getUserPlants(): Observable<UserPlant[]>{
    return this.http.get<UserPlant[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserPlantService.getUserPlants(): error retrieving plants: ' + err)
        );
      })
    );
  }

   create(plantSpeciesId: number, userPlant: UserPlant): Observable<UserPlant> {
   return this.http.post<UserPlant>(this.url + `/plantSpecies/` +  plantSpeciesId, userPlant, this.getHttpOptions()).pipe(
    catchError((err: any) => {
            console.log(err);
            return throwError(
              () => new Error('❌ UserPlantService.create(): error creating plant: ' + err)
            );
          })
        );
}
edit(userPlantId: number, userPlant: UserPlant): Observable<UserPlant> {
   return this.http.put<UserPlant>(this.url + `/` +   userPlantId, userPlant, this.getHttpOptions()).pipe(
    catchError((err: any) => {
            console.log(err);
            return throwError(
              () => new Error('❌ userplantservice.edit(): error updating plant: ' + err)
            );
          })
        );
}

delete(plantSpeciesId: number): Observable<void> {
   return this.http.delete<void>(this.url + `/`
    +  plantSpeciesId, this.getHttpOptions()).pipe(
    catchError((err: any) => {
            console.log(err);
            return throwError(
              () => new Error('❌ UserPlantService.delete(): error deleting plant: ' + err)
            );
          })
        );
}


viewDetails(userPlantId: number): Observable<UserPlant> {
    return this.http.get<UserPlant>(this.url + '/' + userPlantId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserPlantService.viewDetails(): error retrieving user plant: ' + err)
        );
      })
    );
  }
  getHttpOptions() {
    let httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-with': 'XMLHttpRequest',
      },
    };
    return httpOptions;
  }
}
