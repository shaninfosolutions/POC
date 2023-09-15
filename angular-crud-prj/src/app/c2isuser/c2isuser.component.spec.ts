import { ComponentFixture, TestBed } from '@angular/core/testing';

import { C2isuserComponent } from './c2isuser.component';

describe('C2isuserComponent', () => {
  let component: C2isuserComponent;
  let fixture: ComponentFixture<C2isuserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [C2isuserComponent]
    });
    fixture = TestBed.createComponent(C2isuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
