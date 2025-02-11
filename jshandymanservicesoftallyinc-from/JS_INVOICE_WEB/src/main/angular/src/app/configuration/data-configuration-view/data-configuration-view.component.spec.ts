import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataConfigurationViewComponent } from './data-configuration-view.component';

describe('DataConfigurationViewComponent', () => {
  let component: DataConfigurationViewComponent;
  let fixture: ComponentFixture<DataConfigurationViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DataConfigurationViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DataConfigurationViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
