import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Payment } from 'src/app/models/payment.model';
import { ConfirmationDialogComponent } from '../../confirmation-dialog/confirmation-dialog.component';
import { NewPaimentComponent } from '../new-paiment/new-paiment.component';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.css']
})
export class PaymentListComponent implements OnInit, OnChanges {


  // Payment

  displayedColumns: string[] = ['description', 'amountPaind', 'typePayment','payday','actions'];
  dataSourcePayment: MatTableDataSource<Payment> = new MatTableDataSource<Payment>();
  @Input() paymentlist: Array<Payment>;
  @Output() edidPaymentEvent = new EventEmitter<Payment>();
  @Output() deletePayment = new EventEmitter<Payment>();

  // payday: Date;
  // typePayment: string;
  // billNumberAsociate: string;
  // descriptionOfPaind: string;
  // idPayment: number;
  // idWork: number;
  // idEstimate: number;
  // amountPaind: number;

  constructor(private dialog: MatDialog) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.updatedataSourse(this.paymentlist);
  }


  ngOnInit(): void {
    if (this.paymentlist !== undefined && this.paymentlist.length > 0) {
      this.dataSourcePayment.data = this.paymentlist;
    }
  }

  updatedataSourse(paymentlist: Array<Payment>): void {
    this.paymentlist = paymentlist;
    this.dataSourcePayment.data = this.paymentlist;
  }


  onAddNew(editar?: boolean, row?: any): void {
    let payment = new Payment();
    editar !== undefined && editar != null && editar ? payment = row : editar = false;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = 'auto';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      atributo: payment,
      edit: editar,
    };

    const dialogRef = this.dialog.open(NewPaimentComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((x: Payment) => {

      if (x !== undefined) {
        const lista = this.paymentlist.filter(y => y !== row);
        this.deletePayment.emit(row);
        lista.push(x);
        this.edidPaymentEvent.emit(x);
        this.paymentlist = lista;
        this.updatedataSourse(this.paymentlist);
      }
    });
  }

  onRemove(row?: Payment): void {
    this.notificationRemove(row);
  }


  notificationRemove(row?: Payment): void {

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
        const listaServicios = this.paymentlist.filter(y => y != row);
        this.paymentlist = listaServicios;
        this.updatedataSourse(this.paymentlist);
        this.deletePayment.emit(row);
      }
    });
  }
}
