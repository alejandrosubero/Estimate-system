import { TestBed } from '@angular/core/testing';

import { SanitizerPipeService } from '../sanitizer-pipe.service';

describe('SanitizerPipeService', () => {
  let service: SanitizerPipeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SanitizerPipeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
