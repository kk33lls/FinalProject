import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCareLog } from './new-care-log';

describe('NewCareLog', () => {
  let component: NewCareLog;
  let fixture: ComponentFixture<NewCareLog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewCareLog]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewCareLog);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
