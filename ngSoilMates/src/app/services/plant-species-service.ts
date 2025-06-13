import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { PlantSpecies } from '../models/plant-species';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlantSpeciesService {
private url = environment.baseUrl + 'api/plantSpecies';

constructor(private http: HttpClient) { }

keywordSearch(keyword: string): Observable<PlantSpecies[]> {
    return this.http.get<PlantSpecies[]>(this.url + '/search/' + keyword).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PlantSpeciesService.index(): error retrieving plant species: ' + err)
        );
      })
    );
  }
}
