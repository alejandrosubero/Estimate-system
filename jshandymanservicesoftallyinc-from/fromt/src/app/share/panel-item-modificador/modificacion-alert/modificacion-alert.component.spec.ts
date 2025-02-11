import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificacionAlertComponent } from './modificacion-alert.component';

describe('ModificacionAlertComponent', () => {
  let component: ModificacionAlertComponent;
  let fixture: ComponentFixture<ModificacionAlertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModificacionAlertComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificacionAlertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
