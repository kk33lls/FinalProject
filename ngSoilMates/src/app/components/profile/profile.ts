import { UserPlantsService } from './../../services/user-plants-service';

import { AfterViewInit, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Component } from '@angular/core';
import { User } from '../../models/user';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { UserService } from '../../services/user-service';
import { AuthService } from '../../services/auth-service';
import { PlantSpecies } from '../../models/plant-species';
import { UserPlant } from '../../models/user-plant';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  imports: [FormsModule, RouterModule],
  templateUrl: './profile.html',
  styleUrl: './profile.css',

})
export class Profile implements OnInit{
  user: User = new User();
  userPlants: UserPlant[] = []
  editUser: User | null = null;
  searchTerm: string = '';
  // @ViewChild('floatingCard') floatingCardRef!: ElementRef;

  constructor(
    private auth: AuthService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private userPlantsService: UserPlantsService
   ){}

  ngOnInit(): void {
  this.loadUser();
  this.loadUserPlants();
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

  deleteUserPlant( id:number) : void {
   this.userPlantsService.delete(id).subscribe({
    next: (success) => {
      this.loadUser();
    },
    error: (err) => {
      console.log(err);
        console.error('❌error deleting id:', err);
    }
   });
  }













}
