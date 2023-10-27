import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PspdfkitViewerComponent } from './pspdfkit-viewer.component';

describe('PspdfkitViewerComponent', () => {
  let component: PspdfkitViewerComponent;
  let fixture: ComponentFixture<PspdfkitViewerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PspdfkitViewerComponent]
    });
    fixture = TestBed.createComponent(PspdfkitViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
