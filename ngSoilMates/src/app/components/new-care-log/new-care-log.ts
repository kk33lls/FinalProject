import { CareType } from './../../models/care-type';
import { UserPlantsService } from '../../services/user-plants-service';
import { FormsModule } from '@angular/forms';
import { CareLog, CareLogsService } from '../../services/care-logs-service';
import { Component } from '@angular/core';
import { UserPlant } from '../../models/user-plant';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CareTypesService } from '../../services/care-types-service';

@Component({
  selector: 'app-view-care-logs',
  imports: [FormsModule, RouterModule],
  templateUrl: './new-care-log.html',
  styleUrl: './new-care-log.css',
})
export class NewCareLog {
  newCareLog: CareLog = new CareLog();
  showForm: boolean = true;
  careTypes: CareType[] = [];
  userPlantId: number = 0;
  careLogs: CareLog[] = [];
  editLog: CareLog | null = null;

  constructor(
    private careLogService: CareLogsService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private careTypesService: CareTypesService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe({
      next: (params) => {
        let userPlantIdStr = params.get('userPlantId');
        let careLogIdStr = params.get('careLogId');
        if (userPlantIdStr) {
          let userPlantId = parseInt(userPlantIdStr);
          if (isNaN(userPlantId)) {
            this.router.navigateByUrl('notFound');
          } else {
            console.log('Navigate with Id: ' + userPlantId);
            this.userPlantId = userPlantId;
            this.loadCareTypes();
          }
        } if (careLogIdStr) {
          let careLogId = parseInt(careLogIdStr);
          if (isNaN(careLogId)) {
            this.router.navigateByUrl('notFound');
          } else {
            console.log('Navigate with Id: ' + careLogId);
            this.loadCareLog(careLogId);
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

  loadCareLogs(): void {
    this.careLogService.getCareLogs(this.userPlantId).subscribe({
      next: (careLog) => {
        this.careLogs = careLog;
      },
      error: (err) => {
        console.error('Error loading care logs:', err);
        this.router.navigateByUrl('notFound');
      },
    });
  }
  loadCareLog(careLogId: number): void {
    this.careLogService.show(this.userPlantId, careLogId ).subscribe({
      next: (careLog) => {
        this.editLog = careLog;
      },
      error: (err) => {
        console.error('Error loading care logs:', err);
        this.router.navigateByUrl('notFound');
      },
    });
  }
  create(): void {
    this.careLogService.create(this.userPlantId, this.newCareLog).subscribe({
      next: (careLog) => {

        this.router.navigateByUrl('viewUserPlant/' + this.userPlantId);
      },
      error: (err) => {
        console.error('Error adding care log:', err);
      },
    });
  }

  updateCareLog() {
    if(this.editLog){
    this.careLogService.edit(this.userPlantId, this.editLog).subscribe({
      next: (success) => {
     this.router.navigateByUrl('viewUserPlant/' + this.userPlantId);
      },
      error: (err) => {
        console.error('Error editing care log:', err);
        this.router.navigateByUrl('notFound');
      },
    });
  }
}
}
