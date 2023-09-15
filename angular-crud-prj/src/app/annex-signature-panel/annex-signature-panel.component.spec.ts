import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnexSignaturePanelComponent } from './annex-signature-panel.component';

describe('AnnexSignaturePanelComponent', () => {
  let component: AnnexSignaturePanelComponent;
  let fixture: ComponentFixture<AnnexSignaturePanelComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnnexSignaturePanelComponent]
    });
    fixture = TestBed.createComponent(AnnexSignaturePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
