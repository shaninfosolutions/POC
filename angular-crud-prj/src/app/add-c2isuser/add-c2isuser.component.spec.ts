import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddC2isuserComponent } from './add-c2isuser.component';

describe('AddC2isuserComponent', () => {
  let component: AddC2isuserComponent;
  let fixture: ComponentFixture<AddC2isuserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddC2isuserComponent]
    });
    fixture = TestBed.createComponent(AddC2isuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
