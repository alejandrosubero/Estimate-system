import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { LogingResponse } from '../models/loging-response.model';
import { AlertMensajeComponent } from '../share/panel-item-modificador/alert-mensaje/alert-mensaje.component';
import { SessionStorageService } from './session-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AlertConfigurationService {

  private seccion: LogingResponse;


  constructor(
    private dialog: MatDialog,
    private sessionService: SessionStorageService,) { }


checkConfiguration(): void {
  this.seccion = this.sessionService.get('UserSession');

  if (!this.seccion.configurationRedy) {
    this.alertConfiguration();
  }

}


  private alertConfiguration(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '300%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      title: 'Alert of system settings',
      msg: `the system don't have configuration, you need contact the administrator for configure the system.`,
      confirm: 'ok'
    };
    const dialogRef = this.dialog.open(AlertMensajeComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(response => {
      if (response) {
      }
    });
  }




}
