import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchEstimateListComponent } from './search-estimate-list.component';

describe('SearchEstimateListComponent', () => {
  let component: SearchEstimateListComponent;
  let fixture: ComponentFixture<SearchEstimateListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchEstimateListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchEstimateListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
