import { CareType } from './../../models/care-type';
import { UserPlantsService } from '../../services/user-plants-service';
import { FormsModule } from '@angular/forms';
import { CareLog, CareLogsService } from '../../services/care-logs-service';
import { Component } from '@angular/core';
import { UserPlant } from '../../models/user-plant';
import { ActivatedRoute, Router } from '@angular/router';
import { CareTypesService } from '../../services/care-types-service';

@Component({
  selector: 'app-view-care-logs',
  imports: [FormsModule],
  templateUrl: './new-care-log.html',
  styleUrl: './new-care-log.css'
})
export class NewCareLog {
newCareLog: CareLog = new CareLog();
showForm: boolean = true;
careTypes: CareType [] = [];
userPlantId: number = 0;

  constructor(
    private careLogService: CareLogsService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private careTypesService: CareTypesService
  ){}

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
          this.userPlantId = userPlantId;
          this.loadCareTypes();
        }
      }
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

  create(): void {
  this.careLogService.create(this.userPlantId, this.newCareLog).subscribe({
    next: (careLog) => {
      this.newCareLog = new CareLog();
      this.showForm = false;
    },
    error: (err) => {
      console.error('Error adding care log:', err);
    }
  });
  }



}
