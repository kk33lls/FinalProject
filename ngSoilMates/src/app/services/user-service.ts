import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { User } from '../models/user';
import { AuthService } from './auth-service';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = environment.baseUrl + 'api/users/id';

  constructor(private http: HttpClient, private auth: AuthService) { }

  viewUserDetails(userId: number): Observable<User> {
    return this.http.get<User>(this.url + '/' + userId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PlantSpeciesService.viewDetails(): error retrieving plant species: ' + err)
        );
      })
    );
  }
  addPlantToUser(userPlantId: number): Observable<User> {
    return this.http.get<User>(this.url + "/" + userId).pipe(
      
    )
  }


}
