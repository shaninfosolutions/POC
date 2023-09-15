import { TestBed } from '@angular/core/testing';

import { C2isuserService } from './c2isuser.service';

describe('C2isuserService', () => {
  let service: C2isuserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(C2isuserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
