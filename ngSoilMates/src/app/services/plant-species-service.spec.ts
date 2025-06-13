import { TestBed } from '@angular/core/testing';

import { PlantSpeciesService } from './plant-species-service';

describe('PlantSpeciesService', () => {
  let service: PlantSpeciesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlantSpeciesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
