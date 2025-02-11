import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppPanelItemServiceComponent } from './app-panel-item-service.component';

describe('AppPanelItemServiceComponent', () => {
  let component: AppPanelItemServiceComponent;
  let fixture: ComponentFixture<AppPanelItemServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppPanelItemServiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppPanelItemServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
