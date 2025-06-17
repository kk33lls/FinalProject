import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCareLogs } from './view-care-logs';

describe('ViewCareLogs', () => {
  let component: ViewCareLogs;
  let fixture: ComponentFixture<ViewCareLogs>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewCareLogs]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewCareLogs);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
