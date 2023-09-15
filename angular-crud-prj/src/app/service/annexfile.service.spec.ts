import { TestBed } from '@angular/core/testing';

import { AnnexfileService } from './annexfile.service';

describe('AnnexfileService', () => {
  let service: AnnexfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnnexfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
