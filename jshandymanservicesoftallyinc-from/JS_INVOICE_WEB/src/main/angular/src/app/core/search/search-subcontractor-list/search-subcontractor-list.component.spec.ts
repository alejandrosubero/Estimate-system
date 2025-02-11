import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchSubcontractorListComponent } from './search-subcontractor-list.component';

describe('SearchSubcontractorListComponent', () => {
  let component: SearchSubcontractorListComponent;
  let fixture: ComponentFixture<SearchSubcontractorListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchSubcontractorListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchSubcontractorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
