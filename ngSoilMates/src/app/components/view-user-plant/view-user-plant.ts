import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserPlant } from '../../models/user-plant';
import { Router, ActivatedRoute } from '@angular/router';
import { PlantSpeciesService } from '../../services/plant-species-service';
import { UserPlantsService } from '../../services/user-plants-service';
import { UserService } from '../../services/user-service';

@Component({
  selector: 'app-view-user-plant',
  imports: [FormsModule],
  templateUrl: './view-user-plant.html',
  styleUrl: './view-user-plant.css',
})
export class ViewUserPlant {
  editUserPlant: UserPlant | null = null;
  selected: UserPlant = new UserPlant();
  updateForm: boolean = false;

  constructor(
    private router: Router,
    private plantSpeciesService: PlantSpeciesService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private userPlantsService: UserPlantsService
  ) {}
  ngOnInit(): void {
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
          }
        }
      },
    });
  }

  loadById(userPlantId: number): void {
    this.userPlantsService.viewDetails(userPlantId).subscribe({
      next: (userPlant) => {
        this.selected = userPlant;
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
        this.editUserPlant = success;
      },
      error: (err) => {
        console.error('error updating plant:', err);
      },
    });
  }

  deleteUserPlant(id: number): void {
    this.userPlantsService.delete(id).subscribe({
      next: (success) => {},
      error: (err) => {
        console.log(err);
        console.error('❌error deleting id:', err);
      },
    });
  }
}
