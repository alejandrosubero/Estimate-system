import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AfterViewInit, Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Subcontractor } from 'src/app/models/subcontractor.model';
import { ConfirmationDialogComponent } from 'src/app/share/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-search-subcontractor-list',
  templateUrl: './search-subcontractor-list.component.html',
  styleUrls: ['./search-subcontractor-list.component.css']
})
export class SearchSubcontractorListComponent implements OnInit, AfterViewInit {



  displayedColumns: string[] = ['idSubContractor', 'description', 'costOfwork', 'totalCost'];
  dataSourceSubcontractor: MatTableDataSource<Subcontractor> = new MatTableDataSource <Subcontractor> ();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  @Input() subcontractorList: Array<Subcontractor>;


  constructor(
    private _liveAnnouncer: LiveAnnouncer,
    private router: Router,
    private dialog: MatDialog,
    private _snackBar: MatSnackBar,) { }


  ngOnInit(): void {
    this.updateDataSource();
  }

  updateDataSource(): void {
    if (this.subcontractorList !== undefined && this.subcontractorList != null && this.subcontractorList.length > 0) {
      this.dataSourceSubcontractor.data =  this.filterData(this.subcontractorList);
      this.paginatorAndSort();
    }
  }

  paginatorAndSort(): void {
    this.dataSourceSubcontractor.paginator = this.paginator;
    this.dataSourceSubcontractor.sort = this.sort;
  }

  ngAfterViewInit(): void { }


  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceSubcontractor.filter = filterValue.trim().toLowerCase();

    if (this.dataSourceSubcontractor.paginator) {
      this.dataSourceSubcontractor.paginator.firstPage();
    }
  }


  filterData(subcontractorList: Array<Subcontractor>): Array<Subcontractor>{
    let list: Array<Subcontractor> = new Array<Subcontractor>();
    // tslint:disable-next-line: max-line-length
    list = subcontractorList.filter(row => (row.idEstimate !== undefined && row.idEstimate !== null) || (row.idWork !== undefined && row.idWork !== null));
    return list;
  }


  onSelectView(row: Subcontractor): void {

    if ((row.idEstimate !== undefined && row.idEstimate !== null) && (row.idWork !== undefined && row.idWork !== null)) {
      const mensg = 'Select one to view';
      this.notification('select View', mensg, 'Invoice', 'Estimate', row.idEstimate, row.idWork);
    } else {
      if ((row.idEstimate !== undefined && row.idEstimate !== null) && (row.idWork === undefined || row.idWork == null)) {
        this.navegateToEstimate(row.idEstimate);
      } else if ((row.idEstimate === undefined || row.idEstimate == null) && (row.idWork !== undefined && row.idWork !== null)) {
        this.onNavegateToInvoice(row.idWork);
      } else {
        const notification = '"the selected item is not associated with an Estimate or an Invoice"';
        this.openSnackBar(notification, 'Close');
      }
    }
  }


  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
  }


  notification(titles: string, mensaje: string, cancels: string, confirms: string, idEstimate: number, idWork: number): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';
    dialogConfig.data = { title: titles, msg: mensaje, cancel: cancels, confirm: confirms };
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((response: boolean) => {

      response ? this.navegateToEstimate(idEstimate) : this.onNavegateToInvoice(idWork);
    });
  }


  navegateToEstimate(idEstimate: number): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/estimate/edid-and-detail'],
      { queryParams: { id: idEstimate } }
    );
  }


  onNavegateToInvoice(idWork: number): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/invoice/edid-and-detail'],
      { queryParams: { id: idWork } }
    );
  }



}
