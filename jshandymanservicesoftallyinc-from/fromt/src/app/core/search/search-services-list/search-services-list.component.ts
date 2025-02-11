import { AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { ServiceHandyManTally } from 'src/app/models/service-handy-man-tally.model';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from 'src/app/share/confirmation-dialog/confirmation-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-search-services-list',
  templateUrl: './search-services-list.component.html',
  styleUrls: ['./search-services-list.component.css']
})
export class SearchServicesListComponent implements OnInit, AfterViewInit, OnChanges {

  // servicesCost: number;
  // overhead: number;
  // idWork: number;
  // idServices: number;
  // idEstimate: number;
  // descriptionOfServicesCost: string;
  // itemDeliteEdit: boolean;


  displayedColumns: string[] = ['idServices', 'descriptionOfServicesCost', 'servicesCost'];
  dataSourceServices: MatTableDataSource<ServiceHandyManTally> = new MatTableDataSource<ServiceHandyManTally>();

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @Input() servicesList: Array<ServiceHandyManTally>;


  constructor(
    private _liveAnnouncer: LiveAnnouncer,
    private router: Router,
    private dialog: MatDialog,
    private _snackBar: MatSnackBar,) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.updateDataSource();
  }


  ngOnInit(): void { }


  updateDataSource(): void {
    if (this.servicesList !== undefined && this.servicesList != null && this.servicesList.length > 0) {
      this.dataSourceServices.data = this.filterData(this.servicesList);
      this.dataSourceServices.paginator = this.paginator;
      this.dataSourceServices.sort = this.sort;
    }
  }


  ngAfterViewInit(): void { }


  paginatorAndSort(): void {
    this.dataSourceServices.paginator = this.paginator;
    this.dataSourceServices.sort = this.sort;
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceServices.filter = filterValue.trim().toLowerCase();

    if (this.dataSourceServices.paginator) {
      this.dataSourceServices.paginator.firstPage();
    }
  }

  announceSortChange(sortState: Sort): void {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  filterData(servicesList: Array<ServiceHandyManTally>): Array<ServiceHandyManTally> {
    let list: Array<ServiceHandyManTally> = new Array<ServiceHandyManTally>();
    // tslint:disable-next-line: max-line-length
    list = servicesList.filter(row => (row.idEstimate !== undefined && row.idEstimate !== null) || (row.idWork !== undefined && row.idWork !== null));
    return list;
  }


  onSelectView(row: ServiceHandyManTally): void {

    if ((row.referenceEstimate !== undefined && row.referenceEstimate !== null)
      && (row.idWork !== undefined && row.idWork !== null)) {
      const mensg = 'Select one to view';
      this.notification('select View', mensg, 'Invoice', 'Estimate', row);
      return;
    }

    if ((row.idEstimate !== undefined && row.idEstimate !== null)
      && (row.referenceEstimate === undefined || row.referenceEstimate == null)
      && (row.idWork === undefined || row.idWork == null)) {
      this.navegateToEstimate(row);
    }

    if ((row.idEstimate === undefined || row.idEstimate == null)
      && (row.idWork !== undefined && row.idWork !== null)) {
      this.onNavegateToInvoice(row);
    }


    if ((row.idEstimate === undefined || row.idEstimate == null)
      && (row.referenceEstimate === undefined || row.referenceEstimate == null)
      && (row.idWork === undefined || row.idWork == null)) {
      const notification = '"the selected item is not associated with an Estimate or an Invoice"';
      this.openSnackBar(notification, 'Close');
    }

  }


  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
  }


  notification(titles: string, mensaje: string, cancels: string, confirms: string, row: ServiceHandyManTally): void {
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


  navegateToEstimate(row: ServiceHandyManTally): void {

    let idNavegate: number;

    if (row.referenceEstimate !== undefined && row.referenceEstimate != null) {
      idNavegate = row.referenceEstimate;
    } else {
      idNavegate = row.idEstimate;
    }

    this.router.navigate(
      ['/jshandy-man-services/menu/estimate/edid-and-detail'],
      { queryParams: { id: idNavegate } }
    );
  }


  onNavegateToInvoice(row: ServiceHandyManTally): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/invoice/edid-and-detail'],
      { queryParams: { id: row.idWork } }
    );
  }

}





