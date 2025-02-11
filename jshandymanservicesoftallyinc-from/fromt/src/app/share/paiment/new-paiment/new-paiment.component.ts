
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogoPayment } from 'src/app/models/dialogo-payment';
import { Payment } from 'src/app/models/payment.model';

@Component({
  selector: 'app-new-paiment',
  templateUrl: './new-paiment.component.html',
  styleUrls: ['./new-paiment.component.css']
})
export class NewPaimentComponent implements OnInit {

  paymentForm = this.fb.group({
    payday: [null, Validators.required],
    descriptionOfPaind: [null, Validators.required],
    amountPaind: [null, Validators.required],
    typePayment: [null],
    billNumberAsociate: [null],
  });

  edit = false;
  payment: Payment;
  odlService: Payment = new Payment();
  dialogoPayment: DialogoPayment = new DialogoPayment();
  title = 'NEW PAYMENT';

  // tslint:disable-next-line: max-line-length
  constructor(
    public snackBar: MatSnackBar,
    private fb: FormBuilder,
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<NewPaimentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    if (data.edit) {
      this.edit = data.edit;
      this.payment = this.newpayment(data.atributo);
      this.title = 'PAYMENT';
      this.updateView();
    } else {
      this.title = 'NEW PAYMENT';
      this.payment = new Payment();
    }
  }

  ngOnInit(): void {
  }


  onSave(): void {
    if (this.paymentForm.valid) {

      this.payment.payday = this.paymentForm.value.payday;
      this.payment.descriptionOfPaind = this.paymentForm.value.descriptionOfPaind;
      this.payment.amountPaind = this.paymentForm.value.amountPaind;
      this.payment.typePayment = this.paymentForm.value.typePayment;
      this.payment.billNumberAsociate = this.paymentForm.value.billNumberAsociate;

      this.dialogRef.close(this.payment);
    } else {
      //TODO: COLOCAR EL ALERT DE LA VISTA DE SEND MAIL
      this.snackBar.open('Fill in all required fields', 'Cerrar');
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }


  updateView(): void {
    this.paymentForm.patchValue({
      payday: this.payment.payday,
      descriptionOfPaind: this.payment.descriptionOfPaind,
      amountPaind: this.payment.amountPaind,
      typePayment: this.payment.typePayment,
      billNumberAsociate: this.payment.billNumberAsociate,
    });
  }


  newpayment(item: Payment): Payment {
    const payment = new Payment();
    if (item.payday !== undefined && item.payday != null) { payment.payday = item.payday; }
    if (item.typePayment !== undefined && item.typePayment != null) { payment.typePayment = item.typePayment; }
    if (item.billNumberAsociate !== undefined && item.billNumberAsociate != null) { payment.billNumberAsociate = item.billNumberAsociate; }
    if (item.descriptionOfPaind !== undefined && item.descriptionOfPaind != null) { payment.descriptionOfPaind = item.descriptionOfPaind; }
    if (item.idPayment !== undefined && item.idPayment != null) { payment.idPayment = item.idPayment; }
    if (item.idWork !== undefined && item.idWork != null) { payment.idWork = item.idWork; }
    if (item.amountPaind !== undefined && item.amountPaind != null) { payment.amountPaind = item.amountPaind; }
    if (item.idEstimate !== undefined && item.idEstimate != null) { payment.idEstimate = item.idEstimate; }
    return payment;
  }


  addEvent(type: string, event: MatDatepickerInputEvent<Date>): void {
    this.payment.payday = this.paymentForm.value.payday;
  }

  onDescription(): void {
    this.payment.descriptionOfPaind = this.paymentForm.value.descriptionOfPaind;
  }

}
