
import { Component } from '@angular/core';
import { User } from '../../models/user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user-service';
import { AuthService } from '../../services/auth-service';
import { PlantSpecies } from '../../models/plant-species';
import { UserPlant } from '../../models/user-plant';

@Component({
  selector: 'app-profile',
  imports: [],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class Profile {
  user: User = new User();
  userPlants: UserPlant[] = []

  constructor(private auth: AuthService, private router: Router, private activatedRoute: ActivatedRoute){}

  ngOnInit(): void {
  this.loadUser();
  this.loadPlants();
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
 loadPlants(): void {
  this.userPlantService.getUserPlants().subscribe({
    next: (plants) => {
      this.userPlants = plants;
    },
    error: (err) => {
      console.error('Error loading plants:', err);
    }
  });
}
}
