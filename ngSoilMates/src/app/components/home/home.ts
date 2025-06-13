import { PlantSpecies } from './../../models/plant-species';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { PlantSpeciesService } from '../../services/plant-species-service';

@Component({
  selector: 'app-home',
  imports: [FormsModule],
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
// ngOnInit(): void {
//     this.reload();
//     this.activatedRoute.paramMap.subscribe({
//       next: (params) => {
//         let todoIdStr = params.get('todoId');
//         if (todoIdStr) {
//           let todoId = parseInt(todoIdStr);
//           if(isNaN(todoId)){
//             this.router.navigateByUrl('notFound');
//           } else {
//             this.loadById(todoId);
//           }
//         }
//       },
//     });
//   }
 searchPlantSpecies(searchTerm: string): void {

 }
}
