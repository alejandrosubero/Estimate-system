import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { WorkListTabletPojo } from 'src/app/models/workListTabletPojo.model';
import { CoreService } from 'src/app/services/core.service';
import { InvoiceWorkService } from 'src/app/services/invoice-work.service';


import * as _ from 'lodash';

@Component({
  selector: 'app-search-invoice-list',
  templateUrl: './search-invoice-list.component.html',
  styleUrls: ['./search-invoice-list.component.css']
})
export class SearchInvoiceListComponent implements OnInit, AfterViewInit, OnChanges {

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  @Input()  dataListWork: Array<WorkListTabletPojo>;

  displayedColumns: string[] = ['idWork', 'owner', 'createDay', 'totalCostWork', 'status'];
  dataSourceInvoice: MatTableDataSource<WorkListTabletPojo> = new MatTableDataSource<WorkListTabletPojo>();



  constructor(
  
    public snackBar: MatSnackBar,
    private _liveAnnouncer: LiveAnnouncer,
    private invoiceWorkService: InvoiceWorkService,
    private router: Router,
    private coreService: CoreService) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.dataSourceInvoice.paginator = this.paginator;
    this.dataSourceInvoice.sort = this.sort;
    this.getData();
  }

  ngAfterViewInit(): void {
    this.dataSourceInvoice.paginator = this.paginator;
    this.dataSourceInvoice.sort = this.sort;
  }


  ngOnInit(): void {
    this.getData();
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceInvoice.filter = filterValue.trim().toLowerCase();
  }



  getData(): void {
    if (this.dataListWork !== undefined && this.dataListWork != null && this.dataListWork.length > 0) {
      let list = new Array<WorkListTabletPojo>();
      list = this.dataListWork;
      list.forEach(workListTabletPojo => {
        workListTabletPojo.totalCostWork = this.coreService.round(workListTabletPojo.totalCostWork);
      });
      this.invoiceWorkService.updateWorkList2(list);
      this.dataSourceInvoice.data = list;
    }
  }


  onNavegateToDetail(row: WorkListTabletPojo): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/invoice/edid-and-detail'],
      { queryParams: { id: row.idWork } }
    );
  }


  filterForselection(arrayInicial, arraySacado): void {
    return _.differenceWith(arrayInicial, arraySacado, _.isEqual);
  }


  onCreateNewWork(): void {
    this.router.navigateByUrl('/jshandy-man-services/menu/invoice/new');
  }


  // crear un seccion de filtros par afiltar por fecha o por years
  applyDateFilter(): void {
    this.dataSourceInvoice.data = this.dataListWork;
    // tslint:disable-next-line: max-line-length
    // this.dataSource.data = this.dataSource.data.filter(e=> e.createDay >= this.form.value.fromDate && e.createDay <= this.form.value.toDate);
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

  decriptClienteInfo(): void {
    this.coreService.startClient().subscribe(x => {
      // console.log('client listo');
    });
  }

}
