import { AfterViewInit, Component, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
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
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from 'src/app/share/confirmation-dialog/confirmation-dialog.component';
import { ClientDetailComponent } from 'src/app/share/client/client-detail/client-detail.component';


@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit, AfterViewInit, OnChanges {

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  displayedColumns: string[] = ['idClient', 'fullName', 'zipCode', 'address', 'phoneNumber'];
  dataSource2: MatTableDataSource<Client> = new MatTableDataSource<Client>();

  dataListClient2 = new Array<Client>();
  title = 'Clients';
  addressMapping = new Map<string, Array<Client>>();

  constructor(
    public snackBar: MatSnackBar,
    private _liveAnnouncer: LiveAnnouncer,
    private clientService: ClientService,
    private router: Router,
    private coreService: CoreService,
    private dialog: MatDialog,) {
    this.onGetData2();
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
    this.clientService.em2.subscribe(y => {
      this.dataListClient2 = y;
      this.dataSource2.data = this.dataListClient2;
    });
  }


  onGetData2(): void {
    let list = new Array<Client>();
    this.clientService.getClients().subscribe(x => {
      console.log('responseFromClient', x);
      // list = x.entidades;
      list = this.filterDuplicateClient(x.entidades);
      this.clientService.updateClientList(list);
    }, (error: any) => {
      this.snackBar.open('FAILL THE RESPONSE OFF SERVER', 'Cerrar');
    });
  }


  filterDuplicateClient(array: Array<Client>): Array<Client> {
    // this.addressMapping = new Map<string, Array<Client>>();
    this.addressMapping.clear();
    let list = new Array<Client>();
    let hash = {};
    list = array.filter(o => hash[o.address] ? false : hash[o.address] = true);
    this.fillMapClient(array, list);
    console.log(list);
    // console.log(JSON.stringify(array));
    return list;
  }

  fillMapClient(array: Array<Client>, list: Array<Client>): void {
    array.forEach(x => {
      list.forEach(y => {
        if (x.address === y.address) {
          if (this.addressMapping.has(y.address)) {
            this.addressMapping.get(y.address).push(x);
          } else {
            let listCliente = new Array<Client>();
            listCliente.push(x);
            this.addressMapping.set(y.address, listCliente);
          }
        }
      });
    });
  }

  onResetData(): void {
    let list = new Array<Client>();
    this.clientService.resetAndGetClients().subscribe(x => {
      // console.log('response-resetAndGetClients', x);
      // list = x.entidades;
      list = this.filterDuplicateClient(x.entidades);
      this.clientService.updateClientList(list);
    }, (error: any) => {
      this.snackBar.open('FAILL THE RESPONSE OFF SERVER', 'Cerrar');
    });
  }


  announceSortChange(sortState: Sort): void {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }


  showClientDetail(listaR: Array<Client>, address: string, nombre: string): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '90%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      list: listaR,
      title1: address,
      title2: nombre
    };
    const dialogRef = this.dialog.open(ClientDetailComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((response: boolean) => {
      // response ? this.navegateToEstimate(row) : this.onNavegateToInvoice(row);
    });
  }

  onNavegateToDetail(row: Client): void {
    let listaR: Array<Client> = this.addressMapping.get(row.address);

    if (listaR.length > 0) {
      this.showClientDetail(listaR, row.address, row.name);
    } else {
      return;
    }

    // if ((row.estimateId !== undefined && row.estimateId != null)
    //   && (row.workId !== undefined && row.workId != null)) {
    //   const mensg = 'Select one to view';
    //   this.notification('select View', mensg, 'Invoice', 'Estimate', row);
    //   return;
    // }

    // if ((row.estimateId !== undefined && row.estimateId !== null)
    //   && (row.workId === undefined || row.workId == null)) {
    //   this.navegateToEstimate(row);
    // }

    // if ((row.estimateId === undefined || row.estimateId == null)
    //   && (row.workId !== undefined && row.workId !== null)) {
    //   this.onNavegateToInvoice(row);
    // }

    // if ((row.estimateId === undefined || row.estimateId == null)
    //   && (row.workId === undefined || row.workId == null)) {
    //   const notification = '"the selected item is not associated with an Estimate or an Invoice"';
    //   this.openSnackBar(notification, 'Close');
    // }
  }

  navegateToEstimate(row: Client): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/estimate/edid-and-detail'],
      { queryParams: { id: row.estimateId } }
    );
  }


  onNavegateToInvoice(row: Client): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/invoice/edid-and-detail'],
      { queryParams: { id: row.workId } }
    );
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

  onCreateNewEstimate(): void {
    this.router.navigateByUrl('/jshandy-man-services/menu/estimate/edid-and-detail');
  }


  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource2.filter = filterValue.trim().toLowerCase();
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
