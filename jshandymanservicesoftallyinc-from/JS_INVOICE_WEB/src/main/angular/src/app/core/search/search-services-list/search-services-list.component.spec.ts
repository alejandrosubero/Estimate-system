import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchServicesListComponent } from './search-services-list.component';

describe('SearchServicesListComponent', () => {
  let component: SearchServicesListComponent;
  let fixture: ComponentFixture<SearchServicesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchServicesListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchServicesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
