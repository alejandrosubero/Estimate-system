import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AlertMensajeComponent } from '../share/panel-item-modificador/alert-mensaje/alert-mensaje.component';


@Injectable({
  providedIn: 'root'
})
export class AlertStatusService {


  private statusList: Array<string> = ['FINALIZED', 'IN PROGRESS', 'CANCELED', 'PAUSE', 'APPROVED', 'SEND', 'CANCELED', 'PAUSE'];

  constructor(private dialog: MatDialog) { }


  checkSattusFinal(status: string): void {
    if (status === this.statusList[0]) {
      this.alertStatusFinal();
    }
  }


  private alertStatusFinal(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '300%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      title: 'Alert of Work Status',
      msg: `A word cannot be finished, without the payments being charged, you can change the status to canceled to close the work and it is not taken for the annual statistics.`,
      confirm: 'ok'
    };
    const dialogRef = this.dialog.open(AlertMensajeComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(response => {
      if (response) {
      }
    });
  }

}
