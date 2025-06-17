import { TestBed } from '@angular/core/testing';

import { CareLog } from './care-logs-service';

describe('CareLogs', () => {
  let service: CareLog;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CareLog);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
