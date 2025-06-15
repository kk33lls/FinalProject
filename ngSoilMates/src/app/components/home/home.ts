import { PlantSpecies } from './../../models/plant-species';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {  ActivatedRoute, Router, RouterLink } from '@angular/router';
import { PlantSpeciesService } from '../../services/plant-species-service';
import { UserService } from '../../services/user-service';

@Component({
  selector: 'app-home',
  imports: [FormsModule, RouterLink],

  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  plantSpecies: PlantSpecies = new PlantSpecies();
  plantSpeciesSearchResults: PlantSpecies[] = [];
  searchTerm: string = '';

  constructor(
    private plantSpeciesService: PlantSpeciesService,
    private router: Router,
    private userService: UserService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
  this.route.queryParams.subscribe(params => {
    const keyword = params['search'];
    if (keyword) {
      this.searchTerm = keyword;
      this.findPlantSpecies(keyword);
    }
  });
  }

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
  togglePlantAdd(species: PlantSpecies): void {
    this.userService.addPlantToUser(species.id).subscribe({
      next: () => {
        console.log(`Plant ${species.commonNames} added to profile`);
      },
      error: (err) => {
        console.error('Error adding plant:', err);
      },
    });
  }
}
