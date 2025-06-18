import { CareType } from './../../models/care-type';
import { ReminderService } from './../../services/reminder-service';
import { PlantSpeciesService } from './../../services/plant-species-service';
import { PlantSpecies } from './../../models/plant-species';
import { UserPlantsService } from './../../services/user-plants-service';

import { AfterViewInit, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Component } from '@angular/core';
import { User } from '../../models/user';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { UserService } from '../../services/user-service';
import { AuthService } from '../../services/auth-service';

import { UserPlant } from '../../models/user-plant';
import { FormsModule } from '@angular/forms';
import { Reminder } from '../../models/reminder';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-profile',
  imports: [FormsModule, RouterModule, DatePipe],
  templateUrl: './profile.html',
  styleUrl: './profile.css',

})
export class Profile implements OnInit{
  user: User = new User();
  userPlants: UserPlant[] = []
  editUser: User | null = null;
  editUserPlant: UserPlant | null = null;
  searchTerm: string = '';
  plantSpecies: PlantSpecies | null = null;
  reminders: Reminder[] = [];
  newReminder: Reminder = new Reminder();
  // @ViewChild('floatingCard') floatingCardRef!: ElementRef;

  constructor(
    private auth: AuthService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private userPlantsService: UserPlantsService,
    private plantSpeciesService: PlantSpeciesService,
    private reminderService: ReminderService

   ){}

  ngOnInit(): void {
  this.loadUser();
  this.loadUserPlants();
  this.loadReminders();

  }
  goToSearch(): void {
    this.router.navigate(['/home'], { queryParams: { search: this.searchTerm } });
  }

  loadUser(): void{
    this.auth.getLoggedInUser().subscribe({
      next:(selectedUser) => {
        this.user = selectedUser;
        console.log(selectedUser)
      },
      error: (err) => {
        console.error(err);
        console.error("profile.ts Component: error loading user");
        this.router.navigateByUrl("notFound")
      }
    })
  }
  setEditUser(){
    this.editUser = Object.assign({}, this.user)
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
  updateUser(updateUser: User){
    this.userService.update(updateUser, updateUser.id).subscribe({
      next: (success) => {
        this.loadUser();
        this.editUser = null;
        // this.user = null;
      },
      error: (err) => {
        console.log(err);
        console.error('Error updating todo');
      },
    });
  }
  loadUserPlants(): void {
    this.userPlantsService.getUserPlants().subscribe({
      next: (plants) => {
        this.userPlants = plants;
      },
      error: (err) => {
        console.error('Error loading plants:', err);
      }
    });
  }
  updateUserPlant(userPlant: UserPlant) {
    this.userPlantsService.edit(userPlant.id, userPlant).subscribe ({
      next: (success) => {
        this.loadUserPlants();
        this.loadUser();
        this.editUserPlant = null;
      },
       error: (err) => {
        console.error('error updating plant:', err);
       }
    });
  }

  deleteUserPlant( id:number) : void {
   this.userPlantsService.delete(id).subscribe({
    next: (success) => {
      this.loadUser();
      this.loadUserPlants();
    },
    error: (err) => {
      console.log(err);
        console.error('❌error deleting id:', err);
    }
   });
  }
   createReminder(userPlantId: number, careTypeId: number, reminder: Reminder): void {
      this.reminderService.create(userPlantId, careTypeId, reminder).subscribe({
        next: (reminder) => {
          this.newReminder = new Reminder();
           this.loadReminders();
            // this.showReminderForm = false;
        },
        error: (err) => {
          console.error('Error creating reminder:', err);
        },
      });
    }
    updateReminder(userPlantId: number, careTypeId: number, reminder: Reminder): void {
      this.reminderService.edit(userPlantId, careTypeId, reminder).subscribe({
        next: (reminder) => {
        this.loadReminders();
        },
        error: (err) => {
          console.error('Error updating reminder:', err);
        },
      });
    }












}
