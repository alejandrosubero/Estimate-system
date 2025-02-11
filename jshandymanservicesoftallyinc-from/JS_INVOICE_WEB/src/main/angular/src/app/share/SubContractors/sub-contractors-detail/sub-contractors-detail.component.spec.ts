import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubContractorsDetailComponent } from './sub-contractors-detail.component';

describe('SubContractorsDetailComponent', () => {
  let component: SubContractorsDetailComponent;
  let fixture: ComponentFixture<SubContractorsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubContractorsDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubContractorsDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
