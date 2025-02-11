import { TestBed } from '@angular/core/testing';

import { AlertConfigurationService } from '../alert-configuration.service';

describe('AlertConfigurationService', () => {
  let service: AlertConfigurationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AlertConfigurationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
