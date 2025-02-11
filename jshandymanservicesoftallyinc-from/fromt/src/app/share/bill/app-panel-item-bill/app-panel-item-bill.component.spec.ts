import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppPanelItemBillComponent } from './app-panel-item-bill.component';

describe('AppPanelItemBillComponent', () => {
  let component: AppPanelItemBillComponent;
  let fixture: ComponentFixture<AppPanelItemBillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppPanelItemBillComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppPanelItemBillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
