import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppPanelItemProductComponent } from './app-panel-item-product.component';

describe('AppPanelItemProductComponent', () => {
  let component: AppPanelItemProductComponent;
  let fixture: ComponentFixture<AppPanelItemProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppPanelItemProductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppPanelItemProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
