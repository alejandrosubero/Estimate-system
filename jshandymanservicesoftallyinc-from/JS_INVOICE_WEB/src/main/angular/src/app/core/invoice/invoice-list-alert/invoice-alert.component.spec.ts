import { ComponentFixture, TestBed } from '@angular/core/testing';
import { InvoiceAlertComponent } from './invoice-alert.component';



describe('InvoiceAlertComponent', () => {
  let component: InvoiceAlertComponent;
  let fixture: ComponentFixture<InvoiceAlertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvoiceAlertComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceAlertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
