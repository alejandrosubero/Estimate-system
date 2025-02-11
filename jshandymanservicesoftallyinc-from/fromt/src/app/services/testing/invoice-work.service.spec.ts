import { TestBed } from '@angular/core/testing';

import { InvoiceWorkService } from '../invoice-work.service';

describe('InvoiceWorkService', () => {
  let service: InvoiceWorkService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InvoiceWorkService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
