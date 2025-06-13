import { PlantSpeciesService } from './../../services/plant-species-service';
import { ActivatedRoute, Router } from '@angular/router';
import { PlantSpecies } from './../../models/plant-species';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-plant-species',
  imports: [FormsModule],
  templateUrl: './view-plant-species.html',
  styleUrl: './view-plant-species.css'
})
export class ViewPlantSpecies {

  plantSpecies: PlantSpecies = new PlantSpecies();
  plantSpeciesDetailView: PlantSpecies[] = [];
  selected: PlantSpecies | null = null;

  constructor(private router: Router, private plantSpeciesService: PlantSpeciesService, private activatedRoute: ActivatedRoute){}

  ngOnInit(): void {
  this.reload();

   this.activatedRoute.paramMap.subscribe({
    next: (params) => {
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

  reload() {
    this.plantSpeciesService.index().subscribe({
      next: (speciesList) => {
        this.plantSpeciesDetailView = speciesList;
      },
      error: (err) => {
        console.log(err);
        console.log('view-plant-species.ts Component: error loading species');
      }
    });
  }

  displayPlantSpecies(plantSpecies: PlantSpecies): void{
    this.selected = plantSpecies;
  }

}
