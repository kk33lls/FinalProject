import { TestBed } from '@angular/core/testing';

import { CareLogs } from './care-logs';

describe('CareLogs', () => {
  let service: CareLogs;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CareLogs);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
