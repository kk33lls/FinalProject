import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUserPlant } from './view-user-plant';

describe('ViewUserPlant', () => {
  let component: ViewUserPlant;
  let fixture: ComponentFixture<ViewUserPlant>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewUserPlant]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewUserPlant);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
