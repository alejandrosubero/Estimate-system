import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Bill } from 'src/app/models/bill.model';
import { DialogoBill } from 'src/app/models/Dialogo-bill';


@Component({
  selector: 'app-app-panel-item-bill',
  templateUrl: './app-panel-item-bill.component.html',
  styleUrls: ['./app-panel-item-bill.component.css']
})
export class AppPanelItemBillComponent implements OnInit {

  // tslint:disable-next-line: max-line-length
  mensaje = `By accepting, you will remove the item from the list of calculations for the total cost, you can modify the price of the item.`;
  title = `Item Modifier Panel`;

  // checkedbox configuration
  checked = false;
  disabled = false;
  indeterminate = false;
  labelPosition: 'before' | 'after' = 'before';
  color = 'warn';

  inputForm = this.fb.group({
    priceOrTotal: [null, Validators.required],
  });

  item: Bill = new Bill();

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AppPanelItemBillComponent>,
    @Inject(MAT_DIALOG_DATA) public data?: any) {
    if (data.item !== undefined && data.item != null) { this.item = data.item; }
  }


  ngOnInit(): void {
  }


  onAgree(): void {
    const dialogoTranfer = new DialogoBill();
    dialogoTranfer.oldBill = this.item;
    
    if (this.checked) {
      dialogoTranfer.newBill = this.copyItem(this.item);
      if (this.inputForm.value.priceOrTotal !== undefined && this.inputForm.value.priceOrTotal != null) {
        dialogoTranfer.newBill.billTotal = this.inputForm.value.priceOrTotal;
      }
    }
    
    dialogoTranfer.oldBill.itemDeliteEdit = true;
    // dialogoTranfer.oldBill.billTotal = 0;
    // dialogoTranfer.oldBill.billTotalWichoutTaxes = 0;
    this.dialogRef.close(dialogoTranfer);
  }


  copyItem(item: Bill): Bill {
    const bill = new Bill();

    bill.billNumber = item.billNumber;
    bill.billType = item.billType;
    bill.description = item.description;
    bill.productsAndServices = item.productsAndServices;

    bill.billTotal = 0;
    bill.billTotalWichoutTaxes = 0;
    return bill;
  }


  onCancel(): void {
    this.dialogRef.close();
  }


  changePrices(): void {
    console.log(this.checked);
  }
}
