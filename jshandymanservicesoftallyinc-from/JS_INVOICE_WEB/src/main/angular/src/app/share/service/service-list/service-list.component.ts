import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ServiceHandyManTally } from 'src/app/models/service-handy-man-tally.model';
import { NewServiceComponent } from '../new-service/new-service.component';
import { Output, EventEmitter } from '@angular/core';
import { ConfirmationDialogComponent } from '../../confirmation-dialog/confirmation-dialog.component';
import { DialogoService } from 'src/app/models/Dialogo-services.model';


@Component({
  selector: 'app-service-list',
  templateUrl: './service-list.component.html',
  styleUrls: ['./service-list.component.css']
})
export class ServiceListComponent implements OnInit, OnChanges {

  displayedColumns: string[] = ['Description', 'TotalCost', 'Actions'];
  dataSourceServiceList: MatTableDataSource<ServiceHandyManTally> = new MatTableDataSource<ServiceHandyManTally>();
  @Input() servicelist: Array<ServiceHandyManTally>;

  @Output() edidServiceEvent = new EventEmitter<ServiceHandyManTally>();
  @Output() deleteService = new EventEmitter<ServiceHandyManTally>();

  constructor(private dialog: MatDialog) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.updatedataSourse(this.servicelist);
  }


  ngOnInit(): void {
    if (this.servicelist !== undefined && this.servicelist.length > 0) {
      this.dataSourceServiceList.data = this.servicelist;
    }
  }

  updatedataSourse(servicelist: Array<ServiceHandyManTally>): void {
    this.servicelist = servicelist;
    this.dataSourceServiceList.data = this.servicelist;
  }



  onAddNewService(editar?: boolean, serviceRow?: any): void {

    let service = new ServiceHandyManTally();
    editar !== undefined && editar != null && editar ? service = serviceRow : editar = false;

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = 'auto';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      atributo: service,
      edit: editar,
    };

    const dialogRef = this.dialog.open(NewServiceComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((x: DialogoService) => {

      if (x !== undefined) {
        const listaServicios = this.servicelist.filter(y => y != serviceRow);
        this.deleteService.emit(serviceRow);

        if (x.oldService !== undefined && x.oldService != null && x.oldService.itemDeliteEdit
          && x.newService === undefined || x.newService == null) {
          listaServicios.push(x.oldService);
          this.edidServiceEvent.emit(x.oldService);
        }

        if (x.oldService !== undefined && x.oldService != null
          && x.oldService.descriptionOfServicesCost !== undefined && x.oldService.descriptionOfServicesCost != null
          && x.oldService.itemDeliteEdit && x.newService !== undefined && x.newService != null) {
          listaServicios.push(x.oldService);
          listaServicios.push(x.newService);
          this.edidServiceEvent.emit(x.oldService);
          this.edidServiceEvent.emit(x.newService);
        }

        if (x.oldService === undefined && x.oldService == null
          && !x.newService.itemDeliteEdit && x.newService !== undefined && x.newService != null) {
            listaServicios.push(x.newService);
          this.edidServiceEvent.emit(x.newService);
        }

        this.servicelist = listaServicios;
        this.updatedataSourse(this.servicelist);
      }
    });
  }

  
  onRemove(serviceRow?: ServiceHandyManTally): void {
    this.notificationRemove(serviceRow);
  }



  notificationRemove(serviceRow?: ServiceHandyManTally): void {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      title: ' Table data deletion alert',
      msg: 'Are you sure you want to delete the service?',
      cancel: 'Cancel',
      confirm: 'Confirm'
    };

    const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(response => {
      if (response) {
        const listaServicios = this.servicelist.filter(y => y != serviceRow);
        this.servicelist = listaServicios;
        this.updatedataSourse(this.servicelist);
        this.deleteService.emit(serviceRow);
      }
    });
  }


}
