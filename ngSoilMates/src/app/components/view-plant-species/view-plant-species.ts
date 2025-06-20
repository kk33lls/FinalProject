import { UserPlant } from './../../models/user-plant';
import { UserPlantsService } from './../../services/user-plants-service';
import { UserService } from './../../services/user-service';
import { PlantSpeciesService } from './../../services/plant-species-service';
import { ActivatedRoute, Router } from '@angular/router';
import { PlantSpecies } from './../../models/plant-species';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth-service';


@Component({
  selector: 'app-view-plant-species',
  imports: [FormsModule],
  templateUrl: './view-plant-species.html',
  styleUrl: './view-plant-species.css'
})
export class ViewPlantSpecies {
  isAuthenticated = true;
  selected: PlantSpecies = new PlantSpecies();
  showForm: boolean = false;
  userPlant: UserPlant = new UserPlant();

  constructor(private router: Router,
    private plantSpeciesService: PlantSpeciesService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private  userPlantsService: UserPlantsService,
    private authService: AuthService
  ){}

  ngOnInit(): void {
   this.activatedRoute.paramMap.subscribe({
     next: (params) => {
      this.isAuthenticated = this.authService.checkLogin();
      let speciesIdStr = params.get("speciesId");
      if(speciesIdStr) {
        let speciesId = parseInt(speciesIdStr);
        if(isNaN(speciesId)) {
          this.router.navigateByUrl("notFound");
        } else {
          console.log("Navigate with Id: " + speciesId)
          this.loadSpeciesById(speciesId)
        }
      }
    }
  })
  }
  loggedIn(): boolean {
    return this.authService.checkLogin();
  }

  loadSpeciesById(speciesId: number): void {
  this.plantSpeciesService.viewDetails(speciesId).subscribe({
    next: (species) => {
      this.selected = species;
    },
    error: (err) => {
      console.error(err);
      console.error("view-plant-species.ts Component: error loading species");
      this.router.navigateByUrl("notFound")
    }
  });
  }

  createUserPlant(speciesId: number): void {
  this.userPlantsService.create(speciesId, this.userPlant).subscribe({
    next: (userPlant) => {
      this.userPlant = new UserPlant();
      this.showForm = false;
    },
    error: (err) => {
      console.error('Error adding plant:', err);
    }
  });
}
  displayPlantSpecies(plantSpecies: PlantSpecies): void{
    this.selected = plantSpecies;
  }

}
