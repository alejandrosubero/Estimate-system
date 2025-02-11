import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TooltipPosition } from '@angular/material/tooltip';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchDate } from 'src/app/models/Search-date.model';
import { SearchResponse } from 'src/app/models/search-response.model';
import { ServiceHandyManTally } from 'src/app/models/service-handy-man-tally.model';
import { Subcontractor } from 'src/app/models/subcontractor.model';
import { CoreService } from 'src/app/services/core.service';



@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SearchComponent implements OnInit {

  // hoveredDivId = 1;
  // positionOptions: TooltipPosition[] = ['below', 'above', 'left', 'right'];
  // position = new FormControl(this.positionOptions[2]);


  searchResponse: SearchResponse = new SearchResponse();
  panelOpenState = false;
  searchForDate: SearchDate = new SearchDate();

  tileAndForm = this.fb.group({
    starDate: null,
    endDay: null,
    month: null,
    year: null,
  });

  monthValid = false;
  yearValid = false;
  dateSearch: string = 'create';
  title = 'Search';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private coreService: CoreService,
    private fb: FormBuilder,
    private _snackBar: MatSnackBar,) { }

  ngOnInit(): void {
    this.route.queryParams.forEach(params => {
      if (params.id !== undefined || params.id != null) {
        this.search(params.id);
      } else {

      }
    });
  }

  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
  }

  search(keyword: string): void {
    this.coreService.search(keyword).subscribe(x => {
      this.showData(x.entidades[0]);
    });
  }


  showData(entidades): void {
    let response: SearchResponse = new SearchResponse();
    response = entidades;
    response.subcontractors = this.subcontractorFilterData(response.subcontractors);
    response.services = this.serviceFilterData(response.services);
    this.searchResponse = response;
  }


  subcontractorFilterData(subcontractorList: Array<Subcontractor>): Array<Subcontractor> {
    let list: Array<Subcontractor> = new Array<Subcontractor>();
    // tslint:disable-next-line: max-line-length
    list = subcontractorList.filter(row => (row.idEstimate !== undefined && row.idEstimate !== null) || (row.idWork !== undefined && row.idWork !== null));
    return list;
  }

  serviceFilterData(servicesList: Array<ServiceHandyManTally>): Array<ServiceHandyManTally> {
    let list: Array<ServiceHandyManTally> = new Array<ServiceHandyManTally>();
    // tslint:disable-next-line: max-line-length
    list = servicesList.filter(row => (row.idEstimate !== undefined && row.idEstimate !== null) || (row.idWork !== undefined && row.idWork !== null));
    return list;
  }


  addEvent(type: string, event: MatDatepickerInputEvent<Date>): void {
    this.searchForDate.starDate = this.tileAndForm.value.starDate;
  }

  addDedlineEvent(type: string, event: MatDatepickerInputEvent<Date>): void {
    this.searchForDate.endDate = this.tileAndForm.value.endDay;
  }

  addMonth(): void {
    let valor: number = this.tileAndForm.value.month;
    if (valor < 13) {
      this.searchForDate.month = this.tileAndForm.value.month;
      this.monthValid = true;
    } else {
      this.monthValid = false;
      const notification = '"You cannot search in months that do not exist, there are only 12 months in the year."';
      this.openSnackBar(notification, 'Close');
    }
  }


  addYear(): void {
    let valor: number = this.tileAndForm.value.year;
    if (valor > 1980) {
      this.searchForDate.year = this.tileAndForm.value.year;
      this.yearValid = true;
    } else {
      this.yearValid = false;
      const notification = '"You cannot search in years prior to 1980."';
      this.openSnackBar(notification, 'Close');
    }
  }


  searchMonthAndYear(): void {
    if (this.yearValid && this.monthValid) {
      this.searchForDate.clave = this.dateSearch;
      this.coreService.searchMonthAndYear(this.searchForDate).subscribe(x => {
        this.showData(x.entidades[0]);
      });
    }
  }

  searchBetween(): void {
    this.searchForDate.clave = this.dateSearch;
    this.coreService.searchBetween(this.searchForDate).subscribe(x => {
      this.showData(x.entidades[0]);
    });
  }



  // showDivWithHoverStyles(divId: number): void {
  //   this.hoveredDivId = divId;
  // }

  // showAllDivsWithDefaultStyles(): void {
  //   this.hoveredDivId = 1;
  // }


}
