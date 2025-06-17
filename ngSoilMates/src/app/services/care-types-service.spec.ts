import { TestBed } from '@angular/core/testing';

import { CareTypesService } from './care-types-service';

describe('CareTypesService', () => {
  let service: CareTypesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CareTypesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
