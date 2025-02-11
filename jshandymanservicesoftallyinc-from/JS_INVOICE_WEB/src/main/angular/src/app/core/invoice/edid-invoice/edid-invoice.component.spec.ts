import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdidInvoiceComponent } from './edid-invoice.component';

describe('EdidInvoiceComponent', () => {
  let component: EdidInvoiceComponent;
  let fixture: ComponentFixture<EdidInvoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EdidInvoiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EdidInvoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
