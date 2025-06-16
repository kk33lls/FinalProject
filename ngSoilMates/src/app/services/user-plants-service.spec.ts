import { TestBed } from '@angular/core/testing';

import { UserPlantsService } from './user-plants-service';

describe('UserPlantsService', () => {
  let service: UserPlantsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserPlantsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
