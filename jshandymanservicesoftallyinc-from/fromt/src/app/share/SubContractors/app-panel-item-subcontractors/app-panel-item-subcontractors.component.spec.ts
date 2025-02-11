import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppPanelItemSubcontractorsComponent } from './app-panel-item-subcontractors.component';

describe('AppPanelItemSubcontractorsComponent', () => {
  let component: AppPanelItemSubcontractorsComponent;
  let fixture: ComponentFixture<AppPanelItemSubcontractorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppPanelItemSubcontractorsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppPanelItemSubcontractorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
