import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatementUpdateComponent } from './statement-update.component';

describe('StatementUpdateComponent', () => {
  let component: StatementUpdateComponent;
  let fixture: ComponentFixture<StatementUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StatementUpdateComponent]
    });
    fixture = TestBed.createComponent(StatementUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
