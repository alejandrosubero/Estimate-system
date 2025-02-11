
import { AfterViewInit, Component, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { InvoiceWorkService } from 'src/app/services/invoice-work.service';
import { Router } from '@angular/router';
import { CoreService } from 'src/app/services/core.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { MatTableDataSource } from '@angular/material/table';

import * as _ from 'lodash';
import { WorkListTabletPojo } from 'src/app/models/workListTabletPojo.model';
import { Work } from 'src/app/models/work.model';



@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit, AfterViewInit, OnChanges {

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  displayedColumns: string[] = ['idWork', 'owner', 'createDay', 'totalCostWork', 'status'];
  dataSourceInvoice: MatTableDataSource<WorkListTabletPojo> = new MatTableDataSource<WorkListTabletPojo>();

  dataListWork2 = new Array<WorkListTabletPojo>();

  title = 'Invoice';

  constructor(
    public snackBar: MatSnackBar,
    private _liveAnnouncer: LiveAnnouncer,
    private invoiceWorkService: InvoiceWorkService,
    private router: Router,
    private coreService: CoreService) {
    this.onGetData2();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.dataSourceInvoice.paginator = this.paginator;
    this.dataSourceInvoice.sort = this.sort;
  }

  ngAfterViewInit(): void {
    this.dataSourceInvoice.paginator = this.paginator;
    this.dataSourceInvoice.sort = this.sort;
  }


  ngOnInit(): void {
    this.invoiceWorkService.em2.subscribe(y => {
      this.dataListWork2 = y;
      this.dataSourceInvoice.data = this.dataListWork2;
    });
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceInvoice.filter = filterValue.trim().toLowerCase();
  }



  onGetData2(): void {
    let list = new Array<WorkListTabletPojo>();
    this.invoiceWorkService.getList().subscribe(x => {
      list = x.entidades;

      list.forEach(workListTabletPojo => {
        workListTabletPojo.totalCostWork = this.coreService.round(workListTabletPojo.totalCostWork);
      });
      this.invoiceWorkService.updateWorkList2(list);
    }, (error: any) => {
      this.snackBar.open('FAILL THE RESPONSE OFF SERVER', 'Cerrar');
    });
  }


  onNavegateToDetail2(row: WorkListTabletPojo): void {
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
    this.dataSourceInvoice.data = this.dataListWork2;
    // tslint:disable-next-line: max-line-length
    // this.dataSource.data = this.dataSource.data.filter(e=> e.createDay >= this.form.value.fromDate && e.createDay <= this.form.value.toDate);
  }



  announceSortChange(sortState: Sort): void {
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
