import { AfterViewInit, Component, Inject, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
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
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit, AfterViewInit, OnChanges {

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @Input() dataRecive: Array<WorkListTabletPojo>;
  isCurrency = false;
  worksSend: number = 0;
  nameImag: string = '';
  titleCard: string = '';

  displayedColumns: string[] = ['idWork', 'owner', 'createDay', 'totalCostWork', 'totalAmountPaind', 'remainingPayable', 'status'];
  dataSourceInvoice: MatTableDataSource<WorkListTabletPojo> = new MatTableDataSource<WorkListTabletPojo>();
  dataListWorkDetail = new Array<WorkListTabletPojo>();

  title = '';

  constructor(
    public snackBar: MatSnackBar,
    private _liveAnnouncer: LiveAnnouncer,
    private invoiceWorkService: InvoiceWorkService,
    private router: Router,
    private coreService: CoreService,
    private dialogRef: MatDialogRef<DetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,) {

    if (this.data !== undefined && this.data != null) {
      this.dataRecive = data.dataRecive;
      this.isCurrency = data.isCurrency;
      this.worksSend = data.numberWorksSend;
      this.nameImag = data.imagName;
      this.title = data.title;
      this.setData();
    }
  }

  onSelltings(): void {
    this.dialogRef.close();
  }

  setData(): void {
    let list = new Array<WorkListTabletPojo>();
    list = this.dataRecive;
    list.forEach(workListTabletPojo => {
      workListTabletPojo.totalCostWork = this.coreService.round(workListTabletPojo.totalCostWork);
    });
    this.invoiceWorkService.updateWorkListDetail(list);
  }

  ngOnInit(): void {
    this.invoiceWorkService.em2Detail.subscribe(y => {
      this.dataListWorkDetail = y;
      this.dataSourceInvoice.data = this.dataListWorkDetail;
    });
  }


  ngOnChanges(changes: SimpleChanges): void {
    this.dataSourceInvoice.paginator = this.paginator;
    this.dataSourceInvoice.sort = this.sort;
  }

  ngAfterViewInit(): void {
    this.dataSourceInvoice.paginator = this.paginator;
    this.dataSourceInvoice.sort = this.sort;
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceInvoice.filter = filterValue.trim().toLowerCase();
  }


  onNavegateToDetail(row: WorkListTabletPojo): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/invoice/edid-and-detail'],
      { queryParams: { id: row.idWork } }
    );
    this.onSelltings();
  }


  filterForselection(arrayInicial, arraySacado): void {
    return _.differenceWith(arrayInicial, arraySacado, _.isEqual);
  }


  applyDateFilter(): void {
    this.dataSourceInvoice.data = this.dataListWorkDetail;
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
