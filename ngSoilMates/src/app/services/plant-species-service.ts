import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { PlantSpecies } from '../models/plant-species';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from './auth-service';

@Injectable({
  providedIn: 'root'
})
export class PlantSpeciesService {
private url = environment.baseUrl + 'api/plantSpecies';

constructor(private http: HttpClient, private auth: AuthService) { }

index(): Observable<PlantSpecies[]> {
  return this.http.get<PlantSpecies[]>(this.url, this.getHttpOptions()).pipe(
    catchError((err:any) => {
      console.log(err);
      return throwError(
        () => new Error('TodoService.index(): error retrieving todos: ' + err)
      )
    })
  );
  }

keywordSearch(keyword: string): Observable<PlantSpecies[]> {
    return this.http.get<PlantSpecies[]>(this.url + '/search/' + keyword).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PlantSpeciesService.keywordSearch(): error retrieving plant species: ' + err)
        );
      })
    );
  }

viewDetails(speciesId: number): Observable<PlantSpecies> {
    return this.http.get<PlantSpecies>(this.url + '/viewDetails/' + speciesId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PlantSpeciesService.viewDetails(): error retrieving plant species: ' + err)
        );
      })
    );
  }

  getHttpOptions() {
  let options = {
    headers: {
      Authorization: 'Basic ' + this.auth.getCredentials(),
      'X-Requested-With': 'XMLHttpRequest',
    },
  };
  return options;
  }


}
