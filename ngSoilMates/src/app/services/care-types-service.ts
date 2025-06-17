import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { CareType } from '../models/care-type';
import { AuthService } from './auth-service';

@Injectable({
  providedIn: 'root'
})
export class CareTypesService {
  private url = environment.baseUrl + 'api/careTypes';

  constructor(private http: HttpClient, private auth: AuthService) {}

    getcareTypes(): Observable<CareType[]> {
      return this.http
        .get<CareType[]>(
          this.url,
          this.getHttpOptions()
        )
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () =>
                new Error(
                  'CareTypesService.getCareTypes(): error retrieving care types: ' +
                    err
                )
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
