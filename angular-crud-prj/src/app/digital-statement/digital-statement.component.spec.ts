import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DigitalStatementComponent } from './digital-statement.component';

describe('DigitalStatementComponent', () => {
  let component: DigitalStatementComponent;
  let fixture: ComponentFixture<DigitalStatementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DigitalStatementComponent]
    });
    fixture = TestBed.createComponent(DigitalStatementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
