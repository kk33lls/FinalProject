import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { User } from '../models/user';
import { AuthService } from './auth-service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private url = environment.baseUrl + 'api/users';

  constructor(private http: HttpClient, private auth: AuthService) {}

  update(updatedUser: User, userId: number): Observable<User> {
    return this.http.put<User>(this.url + '/' + userId, updatedUser, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('UserService.update(): error updating user: ' + err)
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
  addPlantToUser(plantSpeciesId: number) {
  return this.http.post(`/api/user/plants/${plantSpeciesId}`, null);
}
}
