import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { DialogoSubContractors } from 'src/app/models/Dialogo-subcontractors.model';
import { Subcontractor } from 'src/app/models/subcontractor.model';
import { CoreService } from 'src/app/services/core.service';
import { ConfirmationDialogComponent } from '../../confirmation-dialog/confirmation-dialog.component';
import { NewSubContractorsComponent } from '../new-sub-contractors/new-sub-contractors.component';

@Component({
  selector: 'app-sub-contractors-list',
  templateUrl: './sub-contractors-list.component.html',
  styleUrls: ['./sub-contractors-list.component.css']
})
export class SubContractorsListComponent implements OnInit, OnChanges {

  displayedColumns: string[] = ['Description', 'TotalCost', 'Actions'];
  dataSourceSubContractorsList: MatTableDataSource<Subcontractor> = new MatTableDataSource<Subcontractor>();
  @Input() subcontractorlist: Array<Subcontractor>;

  @Output() edidSubContractorsEvent = new EventEmitter<Subcontractor>();
  @Output() deleteSubContractors = new EventEmitter<Subcontractor>();


  constructor(private dialog: MatDialog, private coreservice: CoreService,) { }


  ngOnChanges(changes: SimpleChanges): void {
    this.updatedatasourse(this.subcontractorlist);

  }

  ngOnInit(): void {
    if (this.subcontractorlist !== undefined && this.subcontractorlist.length > 0) {
      this.dataSourceSubContractorsList.data = this.subcontractorlist;
    }
  }

  updatedatasourse(subcontractorlist: Array<Subcontractor>): void {
    this.dataSourceSubContractorsList.data = this.subcontractorlist;
  }


  onAddNewSubcontractor(editar?: boolean, subcontractorRow?: any): void {

    let service = new Subcontractor();
    editar !== undefined && editar != null && editar ? service = subcontractorRow : editar = false;

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = 'auto';
    dialogConfig.height = '85%';
    dialogConfig.data = {
      atributo: service,
      edit: editar,
    };

    const dialogRef = this.dialog.open(NewSubContractorsComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((x: DialogoSubContractors) => {

      if (x !== undefined) {
        const listaSubcontractor = this.subcontractorlist.filter(y => y != subcontractorRow);
        this.deleteSubContractors.emit(subcontractorRow);

        if (x.newSubContractor !== undefined && x.newSubContractor != null
          && x.newSubContractor.itemDeliteEdit && x.oldSubContractor === undefined || x.oldSubContractor == null) {
          let total = this.coreservice.round(x.newSubContractor.totalCost);
          x.newSubContractor.totalCost = total;
          listaSubcontractor.push(x.newSubContractor);
          this.edidSubContractorsEvent.emit(x.newSubContractor);
        }


        if (x.oldSubContractor !== undefined && x.oldSubContractor != null
          && x.oldSubContractor.itemDeliteEdit && x.newSubContractor !== undefined && x.newSubContractor != null) {
          const totalNew = this.coreservice.round(x.newSubContractor.totalCost);
          x.newSubContractor.totalCost = totalNew;

          const totalOld = this.coreservice.round(x.oldSubContractor.totalCost);
          x.oldSubContractor.totalCost = totalOld;

          listaSubcontractor.push(x.oldSubContractor);
          listaSubcontractor.push(x.newSubContractor);
          this.edidSubContractorsEvent.emit(x.oldSubContractor);
          this.edidSubContractorsEvent.emit(x.newSubContractor);
        }

        this.subcontractorlist = listaSubcontractor;
        this.updatedatasourse(this.subcontractorlist);
      }
    });
  }


  onRemove(subcontractorRow?: Subcontractor): void {
    this.notificationRemove(subcontractorRow);
  }


  notificationRemove(subcontractorRow?: Subcontractor): void {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      title: ' Table data deletion alert',
      msg: 'Are you sure you want to delete the Subcontractor?',
      cancel: 'Cancel',
      confirm: 'Confirm'
    };

    const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(response => {
      if (response) {
        const listaSubcontractor = this.subcontractorlist.filter(y => y != subcontractorRow);
        this.subcontractorlist = listaSubcontractor;
        this.updatedatasourse(this.subcontractorlist);
        this.deleteSubContractors.emit(subcontractorRow);
      }
    });
  }

}

