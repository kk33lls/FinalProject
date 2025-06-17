import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth-service';
import { catchError, Observable, throwError } from 'rxjs';
import { CareLog } from '../models/care-log';

@Injectable({
  providedIn: 'root',
})
export class CareLogsService {
  private url = environment.baseUrl + 'api';

  constructor(private http: HttpClient, private auth: AuthService) {}

  getCareLogs(userPlantId: number): Observable<CareLog[]> {
    return this.http
      .get<CareLog[]>(
        this.url + '/userPlants/' + userPlantId + '/careLogs',
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'CareLogService.getCareLogs(): error retrieving care logs: ' +
                  err
              )
          );
        })
      );
  }

  create(userPlantId: number, careLog: CareLog): Observable<CareLog> {
    return this.http
      .post<CareLog>(
        this.url + `/userPlant/` + userPlantId + '/careLog',
        careLog,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                '❌ CareLogService.create(): error creating care log: ' + err
              )
          );
        })
      );
  }

  // edit(userPlantId: number, userPlant: UserPlant): Observable<UserPlant> {
  //   return this.http
  //     .put<UserPlant>(
  //       this.url + `/` + userPlantId,
  //       userPlant,
  //       this.getHttpOptions()
  //     )
  //     .pipe(
  //       catchError((err: any) => {
  //         console.log(err);
  //         return throwError(
  //           () =>
  //             new Error(
  //               '❌ userplantservice.edit(): error updating plant: ' + err
  //             )
  //         );
  //       })
  //     );
  // }

  // delete(plantSpeciesId: number): Observable<void> {
  //   return this.http
  //     .delete<void>(this.url + `/` + plantSpeciesId, this.getHttpOptions())
  //     .pipe(
  //       catchError((err: any) => {
  //         console.log(err);
  //         return throwError(
  //           () =>
  //             new Error(
  //               '❌ UserPlantService.delete(): error deleting plant: ' + err
  //             )
  //         );
  //       })
  //     );
  // }

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
export { CareLog };

