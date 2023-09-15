import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevisionHistoryComponent } from './revision-history.component';

describe('RevisionHistoryComponent', () => {
  let component: RevisionHistoryComponent;
  let fixture: ComponentFixture<RevisionHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RevisionHistoryComponent]
    });
    fixture = TestBed.createComponent(RevisionHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
