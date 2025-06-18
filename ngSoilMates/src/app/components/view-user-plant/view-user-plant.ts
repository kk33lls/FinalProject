import { CareTypesService } from './../../services/care-types-service';
import { ReminderService } from './../../services/reminder-service';
import { CareLogsService } from './../../services/care-logs-service';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserPlant } from '../../models/user-plant';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { PlantSpeciesService } from '../../services/plant-species-service';
import { UserPlantsService } from '../../services/user-plants-service';
import { UserService } from '../../services/user-service';
import { CommonModule } from '@angular/common';
import { CareLog } from '../../models/care-log';
import { Reminder } from '../../models/reminder';
import { CareType } from '../../models/care-type';
import { PlantSpecies } from '../../models/plant-species';

@Component({
  selector: 'app-view-user-plant',
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './view-user-plant.html',
  styleUrl: './view-user-plant.css',
})
export class ViewUserPlant {
  editUserPlant: UserPlant | null = null;
  selected: UserPlant = new UserPlant();
  updateForm: boolean = false;
  careLogs: CareLog [] = [];
  newReminder: Reminder = new Reminder();
  showReminderForm: boolean = false;
  careTypes: CareType[] = [];
  careType: CareType | null = null;
  reminders: Reminder[] = [];
  plantSpecies: PlantSpecies | null = null;

  constructor(
    private router: Router,
    private plantSpeciesService: PlantSpeciesService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private userPlantsService: UserPlantsService,
    private careLogService: CareLogsService,
    private reminderService: ReminderService,
    private careTypesService: CareTypesService
  ) {}

ngOnInit(): void {
  this.newReminder.careType = new CareType();

  this.activatedRoute.paramMap.subscribe({
    next: (params) => {
      let userPlantIdStr = params.get('userPlantId');
      if (userPlantIdStr) {
        let userPlantId = parseInt(userPlantIdStr);
        if (isNaN(userPlantId)) {
          this.router.navigateByUrl('notFound');
        } else {
          console.log('Navigate with Id: ' + userPlantId);
          this.loadById(userPlantId);
          this.loadCareTypes();
        }
      }
    },
  });
}
  loadCareLogs(): void {
    this.careLogService.getCareLogs(this.selected.id).subscribe({
      next: (careLog) => {
        this.careLogs = careLog;

      },
      error: (err) => {
        console.error('Error loading care logs:', err);
        this.router.navigateByUrl('notFound');
      },
    });
  }
  loadCareTypes(): void {
    this.careTypesService.getcareTypes().subscribe({
      next: (careTypes) => {
        this.careTypes = careTypes;
      },
      error: (err) => {
        console.error(err);
        console.error('view-user-plant.ts Component: error loading plant');
        this.router.navigateByUrl('notFound');
      },
    });
  }
  setEditUserPlant(){
    this.editUserPlant = {...this.selected}
  }

  loadById(userPlantId: number): void {
    this.userPlantsService.viewDetails(userPlantId).subscribe({
      next: (userPlant) => {
        this.selected = userPlant;
        this.loadCareLogs();
         this.loadReminders();
      },
      error: (err) => {
        console.error(err);
        console.error('view-user-plant.ts Component: error loading plant');
        this.router.navigateByUrl('notFound');
      },
    });
  }

  updateUserPlant(userPlant: UserPlant) {
    this.userPlantsService.edit(userPlant.id, userPlant).subscribe({
      next: (success) => {
        this.selected = success;
        this.editUserPlant = null;

      },
      error: (err) => {
        console.error('error updating plant:', err);
      },
    });
  }

  deleteUserPlant(id: number): void {
    this.userPlantsService.delete(id).subscribe({
      next: (success) => {
        this.router.navigateByUrl('/profile');
      },
      error: (err) => {
        console.log(err);
        console.error('❌error deleting id:', err);
      },
    });
  }

  deleteCareLog(userPlantId: number, careLogId: number): void {
    this.careLogService.delete(userPlantId, careLogId).subscribe({
      next: (success) => {
        this.loadCareLogs();
      },
      error: (err) => {
        console.log(err);
        console.error('❌error deleting id:', err);
      },
    });
  }
loadReminders() {
    this.reminderService.getReminders().subscribe({
      next: (rs) => {
        this.reminders = rs
      },
      error: (err) => {
        console.error('Error loading reminders', err)
      }
    });
  }

  createReminder(userPlantId: number, careTypeId: number, reminder: Reminder): void {
    this.reminderService.create(userPlantId, careTypeId, reminder).subscribe({
      next: (reminder) => {
        this.newReminder = new Reminder();
        this.showReminderForm = false;
        this.router.navigateByUrl('profile');
      },
      error: (err) => {
        console.error('Error creating reminder:', err);
      },
    });
  }
}
