import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnexComponent } from './annex.component';

describe('AnnexComponent', () => {
  let component: AnnexComponent;
  let fixture: ComponentFixture<AnnexComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnnexComponent]
    });
    fixture = TestBed.createComponent(AnnexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
