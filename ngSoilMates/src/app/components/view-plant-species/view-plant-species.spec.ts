import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPlantSpecies } from './view-plant-species';

describe('ViewPlantSpecies', () => {
  let component: ViewPlantSpecies;
  let fixture: ComponentFixture<ViewPlantSpecies>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewPlantSpecies]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewPlantSpecies);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
