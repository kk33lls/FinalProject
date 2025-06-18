import { Pipe, PipeTransform } from '@angular/core';
import { Reminder } from '../models/reminder';

@Pipe({
  name: 'incomplete'
})
export class IncompletePipe implements PipeTransform {

  transform(reminders: Reminder[], showAll: boolean): Reminder[] {
    const results: Reminder[] = [];
    if(showAll){
      return reminders;
    }
    for(let reminder of reminders){
      if(reminder.completed === false){
        results.push(reminder);
      }
    }
    return results;
  }

}
