import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillDetailProductListComponent } from './bill-detail-product-list.component';

describe('BillDetailProductListComponent', () => {
  let component: BillDetailProductListComponent;
  let fixture: ComponentFixture<BillDetailProductListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BillDetailProductListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BillDetailProductListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
