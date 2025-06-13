import { PlantSpecies } from './../../models/plant-species';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { PlantSpeciesService } from '../../services/plant-species-service';

@Component({
  selector: 'app-home',
  imports: [FormsModule, RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  plantSpecies: PlantSpecies = new PlantSpecies();
  plantSpeciesSearchResults: PlantSpecies[] = [];
  searchTerm: string = '';

 constructor(
  private plantSpeciesService: PlantSpeciesService,
  private router: Router
 ){}

 findPlantSpecies(keyword: string) {
    this.plantSpeciesService.keywordSearch(keyword).subscribe({
      next: (plantSpeciesList) => {
        this.plantSpeciesSearchResults = plantSpeciesList;
      },
      error: (err) => {
        console.error('Home.findPlantSepcies(): error from service');
        console.error(err);
      },
    });
  }

}
