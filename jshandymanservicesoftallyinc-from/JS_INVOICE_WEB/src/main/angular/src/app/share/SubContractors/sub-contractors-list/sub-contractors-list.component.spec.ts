import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubContractorsListComponent } from './sub-contractors-list.component';

describe('SubContractorsListComponent', () => {
  let component: SubContractorsListComponent;
  let fixture: ComponentFixture<SubContractorsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubContractorsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubContractorsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
