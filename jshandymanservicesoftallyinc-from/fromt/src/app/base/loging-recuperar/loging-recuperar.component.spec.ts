import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogingRecuperarComponent } from './loging-recuperar.component';

describe('LogingRecuperarComponent', () => {
  let component: LogingRecuperarComponent;
  let fixture: ComponentFixture<LogingRecuperarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogingRecuperarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LogingRecuperarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
