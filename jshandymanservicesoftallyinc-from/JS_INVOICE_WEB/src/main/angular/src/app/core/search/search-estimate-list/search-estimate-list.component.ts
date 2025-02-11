import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { EstimateListTabletPojo } from 'src/app/models/estimate-list-tablet-pojo.model';
import { CoreService } from 'src/app/services/core.service';
import { EstimateService } from 'src/app/services/estimate.service';


// _lodash
import * as _ from 'lodash';


@Component({
  selector: 'app-search-estimate-list',
  templateUrl: './search-estimate-list.component.html',
  styleUrls: ['./search-estimate-list.component.css']
})
export class SearchEstimateListComponent implements OnInit, OnChanges, AfterViewInit {


  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  @Input() dataListEstimate: Array<EstimateListTabletPojo>;

  displayedColumns: string[] = ['idEstimate', 'owner', 'createDay', 'totalCostWork', 'status'];

  dataSourceSearchEstimate: MatTableDataSource<EstimateListTabletPojo> = new MatTableDataSource<EstimateListTabletPojo>();



  constructor(
  
    public snackBar: MatSnackBar,
    private _liveAnnouncer: LiveAnnouncer,
    private serviceEstimate: EstimateService,
    private router: Router,
    private coreService: CoreService) {

  }

  ngOnInit(): void {
    this.getData();
  }


  getData(): void {
    let list = new Array<EstimateListTabletPojo>();
    if (this.dataListEstimate !== undefined && this.dataListEstimate != null && this.dataListEstimate.length > 0) {
      list = this.dataListEstimate;
      list.forEach(estimateListTablet => {
        estimateListTablet.totalCostWork = this.serviceEstimate.round(estimateListTablet.totalCostWork);
      });
      this.serviceEstimate.updateEstimateList2(list);
      this.dataSourceSearchEstimate.data = list;
    }
  }


  ngOnChanges(changes: SimpleChanges): void {
    this.getData();
  }

  ngAfterViewInit(): void {
    this.dataSourceSearchEstimate.paginator = this.paginator;
    this.dataSourceSearchEstimate.sort = this.sort;
  }


  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceSearchEstimate.filter = filterValue.trim().toLowerCase();
  }


  decriptClienteInfo(): void {
    this.coreService.startClient().subscribe(x => {
    });
  }


  onNavegateToDetail(row: EstimateListTabletPojo): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/estimate/edid-and-detail'],
      { queryParams: { id: row.idEstimate } }
    );
  }

  announceSortChange(sortState: Sort): void {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }


  filterForselection(arrayInicial, arraySacado): void {
    return _.differenceWith(arrayInicial, arraySacado, _.isEqual);
  }


  // crear un seccion de filtros par afiltar por fecha o por years
  applyDateFilter(): void {
    this.dataSourceSearchEstimate.data = this.dataListEstimate;
    // tslint:disable-next-line: max-line-length
    // this.dataSourceSearchEstimate.data = this.dataSourceSearchEstimate.data.filter(e=> e.createDay >= this.form.value.fromDate && e.createDay <= this.form.value.toDate);
  }


}
