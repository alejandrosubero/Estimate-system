import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaxesConfigurationComponent } from './taxes-configuration.component';

describe('TaxesConfigurationComponent', () => {
  let component: TaxesConfigurationComponent;
  let fixture: ComponentFixture<TaxesConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaxesConfigurationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaxesConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
