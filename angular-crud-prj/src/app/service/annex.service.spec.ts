import { TestBed } from '@angular/core/testing';

import { AnnexService } from './annex.service';

describe('AnnexService', () => {
  let service: AnnexService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnnexService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
