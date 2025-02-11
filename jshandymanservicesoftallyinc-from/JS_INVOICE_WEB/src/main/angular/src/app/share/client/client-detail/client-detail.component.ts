import { AfterViewInit, Component, Inject, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Client } from 'src/app/models/client.model';
import { EstimateListTabletPojo } from 'src/app/models/estimate-list-tablet-pojo.model';


// _lodash
import * as _ from 'lodash';
import { ClientService } from 'src/app/services/client.service';
import { Router } from '@angular/router';
import { CoreService } from 'src/app/services/core.service';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from 'src/app/share/confirmation-dialog/confirmation-dialog.component';


@Component({
  selector: 'app-client-detail',
  templateUrl: './client-detail.component.html',
  styleUrls: ['./client-detail.component.css']
})
export class ClientDetailComponent implements OnInit, AfterViewInit, OnChanges {

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  displayedColumns: string[] = ['idClient', 'from', 'fullName', 'zipCode',  'phoneNumber'];
  dataSource2: MatTableDataSource<Client> = new MatTableDataSource<Client>();

  dataListClient2 = new Array<Client>();
  title = 'Estimates And Invoices Associated To Client';
  addressMapping = new Map<string, Array<Client>>();

  constructor(
    public snackBar: MatSnackBar,
    private _liveAnnouncer: LiveAnnouncer,
    private clientService: ClientService,
    private router: Router,
    private coreService: CoreService,
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<ClientDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    if (data.list != null && data.list !== undefined) {
      // console.log('data:', this.setFrom(data.list));
      // this.dataListClient2 = data.list;
      this.dataListClient2 = this.setFrom(data.list);
      this.dataSource2.data = this.dataListClient2;
      this.title = `${this.title}: ${data.title2} address: ${data.title1}.`;
    }
  }

  setFrom(lista: Array<Client>): Array<Client> {
    lista.forEach(cliente => {
      if (cliente.workId != null && cliente.workId !== undefined) {
        cliente.from = 'Invoice';
      }
      if (cliente.estimateId != null && cliente.estimateId !== undefined) {
        cliente.from = 'Estimate';
      }
    });

    return lista;
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.dataSource2.paginator = this.paginator;
    this.dataSource2.sort = this.sort;
  }

  ngAfterViewInit(): void {
    this.dataSource2.paginator = this.paginator;
    this.dataSource2.sort = this.sort;
  }

  ngOnInit(): void {

  }

  onSelltings(): void {
    this.dialogRef.close();
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource2.filter = filterValue.trim().toLowerCase();
  }


  onNavegateToDetail(row: Client): void {
    if ((row.estimateId !== undefined && row.estimateId != null)
      && (row.workId !== undefined && row.workId != null)) {
      const mensg = 'Select one to view';
      this.notification('select View', mensg, 'Invoice', 'Estimate', row);
      return;
    }

    if ((row.estimateId !== undefined && row.estimateId !== null)
      && (row.workId === undefined || row.workId == null)) {
      this.navegateToEstimate(row);
    }

    if ((row.estimateId === undefined || row.estimateId == null)
      && (row.workId !== undefined && row.workId !== null)) {
      this.onNavegateToInvoice(row);
    }

    if ((row.estimateId === undefined || row.estimateId == null)
      && (row.workId === undefined || row.workId == null)) {
      const notification = '"the selected item is not associated with an Estimate or an Invoice"';
      this.openSnackBar(notification, 'Close');
    }
  }

  navegateToEstimate(row: Client): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/estimate/edid-and-detail'],
      { queryParams: { id: row.estimateId } }
    );
    this.dialogRef.close();
  }


  onNavegateToInvoice(row: Client): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/invoice/edid-and-detail'],
      { queryParams: { id: row.workId } }
    );
    this.dialogRef.close();
  }


  notification(titles: string, mensaje: string, cancels: string, confirms: string, row: Client): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';
    dialogConfig.data = { title: titles, msg: mensaje, cancel: cancels, confirm: confirms };
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((response: boolean) => {
      response ? this.navegateToEstimate(row) : this.onNavegateToInvoice(row);
    });
  }

  openSnackBar(message: string, action: string): void {
    this.snackBar.open(message, action);
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

  applyDateFilter(): void {
    this.dataSource2.data = this.dataListClient2;
    // tslint:disable-next-line: max-line-length
    // this.dataSource.data = this.dataSource.data.filter(e=> e.createDay >= this.form.value.fromDate && e.createDay <= this.form.value.toDate);
  }


}
