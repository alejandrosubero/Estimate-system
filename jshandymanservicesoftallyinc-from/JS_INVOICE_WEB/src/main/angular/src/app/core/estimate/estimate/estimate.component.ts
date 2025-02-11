import { AfterViewInit, Component, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
// import { MatSort } from '@angular/material/sort';
import { MatSort, Sort } from '@angular/material/sort';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { MatTableDataSource } from '@angular/material/table';
import { Estimate } from 'src/app/models/estimate.model';
import { EstimateService } from 'src/app/services/estimate.service';


// _lodash
import * as _ from 'lodash';
import { Router } from '@angular/router';
import { CoreService } from 'src/app/services/core.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EstimateListTabletPojo } from 'src/app/models/estimate-list-tablet-pojo.model';




@Component({
  selector: 'app-estimate',
  templateUrl: './estimate.component.html',
  styleUrls: ['./estimate.component.css']
})
export class EstimateComponent implements OnInit, AfterViewInit, OnChanges {


  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  // public spinner: boolean = false;

  // displayedColumns: string[] = ['id', 'name', 'starDate', 'totalCost', 'status'];
  displayedColumns: string[] = ['idEstimate', 'owner', 'createDay', 'totalCostWork', 'status'];
  // dataSource: MatTableDataSource<Estimate> = new MatTableDataSource<Estimate>();

  dataSource2: MatTableDataSource<EstimateListTabletPojo> = new MatTableDataSource<EstimateListTabletPojo>();

  // startClient = false;
  dataListEstimate = new Array<Estimate>();
  dataListEstimate2 = new Array<EstimateListTabletPojo>();
  title = 'Estimate';

  constructor(
    public snackBar: MatSnackBar,
    private _liveAnnouncer: LiveAnnouncer,
    private serviceEstimate: EstimateService,
    private router: Router,
    private coreService: CoreService) {
    this.onGetData2();
  }

  ngOnInit(): void {

    this.serviceEstimate.em2.subscribe(y => {
      this.dataListEstimate2 = y;
      this.dataSource2.data = this.dataListEstimate2;
    });
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource2.filter = filterValue.trim().toLowerCase();
  }


 
  decriptClienteInfo(): void {
    this.coreService.startClient().subscribe(x => {
    });
  }


  onGetData2(): void {
    let list = new Array<EstimateListTabletPojo>();
    this.serviceEstimate.getListEstimate().subscribe(x => {
      list = x.entidades;
      list.forEach(estimateListTablet => {
        estimateListTablet.totalCostWork = this.serviceEstimate.round(estimateListTablet.totalCostWork);
      });
      this.serviceEstimate.updateEstimateList2(list);
     
    }, (error: any) => {
      this.snackBar.open('FAILL THE RESPONSE OFF SERVER', 'Cerrar');
    });
  }


  onNavegateToDetail2(row: EstimateListTabletPojo): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/estimate/edid-and-detail'],
      { queryParams: { id: row.idEstimate } }
    );
  }


  onGetData(): void {
    let list = new Array<Estimate>();
    this.serviceEstimate.getAllEstimate().subscribe(x => {
      list = x.entidades;
      list.forEach(estimate => {
        estimate.createDay = new Date(estimate.createDay);
        // estimate.starDate = new Date(estimate.starDate);
        estimate.owner = estimate.client.name;
        estimate.totalCostWork = this.serviceEstimate.round(estimate.totalCostWork);
        estimate.totalCostWorkWithoutTaxes = this.serviceEstimate.round(estimate.totalCostWorkWithoutTaxes);
      });
      this.serviceEstimate.updateEstimateList(list);
    }, (error: any) => {
      this.snackBar.open('FAILL THE RESPONSE OFF SERVER', 'Cerrar');
    });
  }


  ngAfterViewInit(): void {
    this.dataSource2.paginator = this.paginator;
    this.dataSource2.sort = this.sort;
  }


  /** Announce the change in sort state for assistive technology. */
  announceSortChange(sortState: Sort) {
    // This example uses English messages. If your application supports
    // multiple language, you would internationalize these strings.
    // Furthermore, you can customize the message to add additional
    // details about the values being sorted.
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }


  ngOnChanges(changes: SimpleChanges): void {
    this.dataSource2.paginator = this.paginator;
    this.dataSource2.sort = this.sort;
  }


  filterForselection(arrayInicial, arraySacado): void {
    return _.differenceWith(arrayInicial, arraySacado, _.isEqual);
  }


  onNavegateToDetail(row: Estimate): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/estimate/edid-and-detail'],
      { queryParams: { id: row.idEstimate } }
    );
  }


  onCreateNewEstimate(): void {
    this.router.navigateByUrl('/jshandy-man-services/menu/estimate/edid-and-detail');
  }


   // crear un seccion de filtros par afiltar por fecha o por years
   applyDateFilter(): void {
    this.dataSource2.data = this.dataListEstimate;
    // tslint:disable-next-line: max-line-length
    // this.dataSource.data = this.dataSource.data.filter(e=> e.createDay >= this.form.value.fromDate && e.createDay <= this.form.value.toDate);
  }

}
