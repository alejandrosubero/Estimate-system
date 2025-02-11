import { TestBed } from '@angular/core/testing';

import { AlertStatusService } from '../alert-status.service';

describe('AlertStatusService', () => {
  let service: AlertStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AlertStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
