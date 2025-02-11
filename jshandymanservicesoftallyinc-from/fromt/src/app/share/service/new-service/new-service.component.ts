import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogoService } from 'src/app/models/Dialogo-services.model';
import { ServiceHandyManTally } from 'src/app/models/service-handy-man-tally.model';
import { AppPanelItemServiceComponent } from '../app-panel-item-service/app-panel-item-service.component';

@Component({
  selector: 'app-new-service',
  templateUrl: './new-service.component.html',
  styleUrls: ['./new-service.component.css']
})
export class NewServiceComponent implements OnInit {


  servicesForm = this.fb.group({
    servicesCost: [null, Validators.required],
    overhead: [null],
    descriptionOfServicesCost: [null, Validators.required],
  });

  edit = false;
  service: ServiceHandyManTally;
  odlService: ServiceHandyManTally = new ServiceHandyManTally();
  dialogoService: DialogoService = new DialogoService();
  titleService = 'NEW SERVICE';

  // tslint:disable-next-line: max-line-length
  constructor(
    public snackBar: MatSnackBar,
    private fb: FormBuilder,
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<NewServiceComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    if (data.edit) {
      this.edit = data.edit;
      this.service = this. newService(data.atributo);
      this.titleService = 'SERVICE';
      this.updateView();
    } else {
      this.titleService = 'NEW SERVICE';
      this.service = new ServiceHandyManTally();
    }
  }

  ngOnInit(): void {
  }


  onSave(): void {
    if (this.servicesForm.valid) {

      if (this.edit && this.odlService !== undefined && this.odlService != null
        && this.odlService.descriptionOfServicesCost !== undefined && this.odlService.descriptionOfServicesCost != null) {
        this.dialogoService.oldService = this.odlService;
      }

      this.service.servicesCost = this.servicesForm.value.servicesCost;
      this.service.descriptionOfServicesCost = this.servicesForm.value.descriptionOfServicesCost;

      if (this.servicesForm.value.overhead !== undefined && this.servicesForm.value.overhead != null) {
        this.service.overhead = this.servicesForm.value.overhead;
      }
      this.dialogoService.newService = this.service;

      this.dialogRef.close(this.dialogoService);
    } else {
      this.snackBar.open('Fill in all required fields', 'Cerrar');
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }


  updateView(): void {
    this.servicesForm.patchValue({
      servicesCost: this.service.servicesCost,
      overhead: this.service.overhead,
      descriptionOfServicesCost: this.service.descriptionOfServicesCost
    });
  }


  panelItem(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      item: this.service,
    };

    const dialogRef = this.dialog.open(AppPanelItemServiceComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((dialogo: DialogoService) => {
      if (dialogo !== undefined && dialogo != null) {
        if (dialogo.oldService.itemDeliteEdit && dialogo.newService !== undefined && dialogo.newService != null) {
          this.titleService = 'NEW SERVICE';
          this.service = dialogo.newService;
          this.odlService = dialogo.oldService;
          this.updateView();
        }

        if (dialogo.oldService.itemDeliteEdit && dialogo.newService === undefined || dialogo.newService == null) {
          this.service = dialogo.oldService;
          this.titleService = 'SERVICE';
          this.updateView();
        }
        // this.dialogoService = dialogo;
      }
    });
  }


  newService(item: ServiceHandyManTally): ServiceHandyManTally {
    const service = new ServiceHandyManTally();
    service.descriptionOfServicesCost = item.descriptionOfServicesCost;
    if (item.servicesCost !== undefined && item.servicesCost != null) { service.servicesCost = item.servicesCost; }
    if (item.idEstimate !== undefined && item.idEstimate != null) { service.idEstimate = item.idEstimate; }
    if (item.idServices !== undefined && item.idServices != null) { service.idServices = item.idServices; }
    if (item.idWork !== undefined && item.idWork != null) { service.idWork = item.idWork; }
    if (item.itemDeliteEdit !== undefined && item.itemDeliteEdit != null) { service.itemDeliteEdit = item.itemDeliteEdit; }
    if (item.overhead !== undefined && item.overhead != null) { service.overhead = item.overhead; }
    return service;
  }


}
