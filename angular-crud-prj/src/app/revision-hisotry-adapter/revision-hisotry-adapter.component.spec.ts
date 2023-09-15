import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevisionHisotryAdapterComponent } from './revision-hisotry-adapter.component';

describe('RevisionHisotryAdapterComponent', () => {
  let component: RevisionHisotryAdapterComponent;
  let fixture: ComponentFixture<RevisionHisotryAdapterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RevisionHisotryAdapterComponent]
    });
    fixture = TestBed.createComponent(RevisionHisotryAdapterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
