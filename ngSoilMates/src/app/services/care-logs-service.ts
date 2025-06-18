import { CareLog } from './../models/care-log';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth-service';
import { catchError, Observable, throwError } from 'rxjs';

import { UserPlant } from '../models/user-plant';

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
  show(userPlantId: number, careLogId: number): Observable<CareLog> {
    return this.http
      .get<CareLog>(
        this.url + '/userPlants/' + userPlantId + '/careLogs/' + careLogId,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'CareLogService.show(): error retrieving care log: ' +
                  err
              )
          );
        })
      );
  }

  create(userPlantId: number, careLog: CareLog): Observable<CareLog> {
    return this.http
      .post<CareLog>(
        this.url + `/userPlants/` + userPlantId + '/careLogs',
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

  edit(userPlantId: number, careLog: CareLog): Observable<CareLog> {
    return this.http.put<CareLog>(
        this.url + `/userPlants/` + userPlantId + '/careLogs/' + careLog.id, careLog,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                '❌ CareLogService.edit(): error updating care log: ' + err
              )
          );
        })
      );
  }

  delete(userPlantId: number, careLogId: number): Observable<CareLog> {
    return this.http.delete<CareLog>(this.url  + `/userPlants/` + userPlantId + '/careLogs/' + careLogId, this.getHttpOptions())
    .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                '❌ CareLogService.delete(): error deleting care log: ' + err
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
export { CareLog };

