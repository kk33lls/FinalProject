import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { AuthService } from './auth-service';
import { CareType } from '../models/care-type';
import { Reminder } from '../models/reminder';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReminderService {
private url = environment.baseUrl + 'api';

  constructor(private http: HttpClient, private auth: AuthService) {}

  getReminders(userPlantId: number): Observable<Reminder[]> {
      return this.http
        .get<Reminder[]>(
          this.url + '/userPlants/' + userPlantId + '/reminders',
          this.getHttpOptions()
        )
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () =>
                new Error(
                  'ReminderService.getReminders(): error retrieving reminders: ' +
                    err
                )
            );
          })
        );
    }

   create(userPlantId: number, careTypeId: number, reminder: Reminder): Observable<Reminder> {
     return this.http
       .post<Reminder>(
         this.url + `/userPlants/` + userPlantId + '/reminders/careTypes' + careTypeId,
         reminder,
         this.getHttpOptions()
       )
       .pipe(
         catchError((err: any) => {
           console.log(err);
           return throwError(
             () =>
               new Error(
                 '❌ ReminderService.create(): error creating reminder: ' + err
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
