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

  getReminders(): Observable<Reminder[]> {
      return this.http
        .get<Reminder[]>(
          this.url + '/userPlants/reminders',
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
         this.url + `/userPlants/` + userPlantId + '/reminders/careTypes/' + careTypeId,
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
    edit(userPlantId: number, careTypeId: number, reminder: Reminder): Observable<Reminder> {
       return this.http.put<Reminder>(
           this.url + `/userPlants/` + userPlantId + '/reminders/' + reminder.id + '/careTypes/' + careTypeId,
           reminder,
           this.getHttpOptions()
         )
         .pipe(
           catchError((err: any) => {
             console.log(err);
             return throwError(
               () =>
                 new Error(
                   '❌ ReminderService.edit(): error updating care log: ' + err
                 )
             );
           })
         );
     }

     delete(userPlantId: number, reminderId: number): Observable<Reminder> {
       return this.http.delete<Reminder>(this.url  + `/userPlants/` + userPlantId + '/reminders/' + reminderId, this.getHttpOptions())
       .pipe(
           catchError((err: any) => {
             console.log(err);
             return throwError(
               () =>
                 new Error(
                   '❌ ReminderService.delete(): error deleting care log: ' + err
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
