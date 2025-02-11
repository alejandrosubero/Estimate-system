import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchInvoiceListComponent } from './search-invoice-list.component';

describe('SearchInvoiceListComponent', () => {
  let component: SearchInvoiceListComponent;
  let fixture: ComponentFixture<SearchInvoiceListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchInvoiceListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchInvoiceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
