import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorTrackChangeComponent } from './editor-track-change.component';

describe('EditorTrackChangeComponent', () => {
  let component: EditorTrackChangeComponent;
  let fixture: ComponentFixture<EditorTrackChangeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditorTrackChangeComponent]
    });
    fixture = TestBed.createComponent(EditorTrackChangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
