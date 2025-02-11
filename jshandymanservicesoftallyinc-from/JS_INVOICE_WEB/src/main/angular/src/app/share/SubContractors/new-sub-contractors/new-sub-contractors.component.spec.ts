import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSubContractorsComponent } from './new-sub-contractors.component';

describe('NewSubContractorsComponent', () => {
  let component: NewSubContractorsComponent;
  let fixture: ComponentFixture<NewSubContractorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewSubContractorsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewSubContractorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
