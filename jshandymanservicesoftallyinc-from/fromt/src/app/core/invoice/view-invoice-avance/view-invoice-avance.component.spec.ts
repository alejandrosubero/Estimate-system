import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewInvoiceAvanceComponent } from './view-invoice-avance.component';

describe('ViewInvoiceAvanceComponent', () => {
  let component: ViewInvoiceAvanceComponent;
  let fixture: ComponentFixture<ViewInvoiceAvanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewInvoiceAvanceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewInvoiceAvanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
