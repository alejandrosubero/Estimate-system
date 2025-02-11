import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPaimentComponent } from './new-paiment.component';

describe('NewPaimentComponent', () => {
  let component: NewPaimentComponent;
  let fixture: ComponentFixture<NewPaimentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewPaimentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewPaimentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
