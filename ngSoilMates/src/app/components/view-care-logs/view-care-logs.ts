import { UserPlantsService } from './../../services/user-plants-service';
import { FormsModule } from '@angular/forms';
import { CareLog, CareLogsService } from './../../services/care-logs-service';
import { Component } from '@angular/core';
import { CareType } from '../../models/care-type';
import { UserPlant } from '../../models/user-plant';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-view-care-logs',
  imports: [FormsModule, RouterLink],
  templateUrl: './view-care-logs.html',
  styleUrl: './view-care-logs.css'
})
export class ViewCareLogs {
newCareLog: CareLog = new CareLog();
showForm: boolean = true;
careTypes: CareType [] = [];
userPlantId: number = 0;

  constructor(
    private careLogService: CareLogsService,
    private activatedRoute: ActivatedRoute,
    private router: Router
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
        }
      }
    },
  });
  }



  create(userPlantId: number): void {
  this.careLogService.create(userPlantId, this.newCareLog).subscribe({
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
