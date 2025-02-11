
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogoSubContractors } from 'src/app/models/Dialogo-subcontractors.model';
import { Subcontractor } from 'src/app/models/subcontractor.model';

@Component({
  selector: 'app-app-panel-item-subcontractors',
  templateUrl: './app-panel-item-subcontractors.component.html',
  styleUrls: ['./app-panel-item-subcontractors.component.css']
})
export class AppPanelItemSubcontractorsComponent implements OnInit {


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

  item: Subcontractor = new Subcontractor();

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AppPanelItemSubcontractorsComponent>,
    @Inject(MAT_DIALOG_DATA) public data?: any) {
    if (data.item !== undefined && data.item != null) { this.item = data.item; }
  }


  ngOnInit(): void {
  }


  onAgree(): void {
    const dialogoTranfer = new DialogoSubContractors();
    dialogoTranfer.oldSubContractor = this.item;

    if (this.checked) {
      dialogoTranfer.newSubContractor = this.copyItem(this.item);
      if (this.inputForm.value.priceOrTotal !== undefined && this.inputForm.value.priceOrTotal != null) {
        dialogoTranfer.newSubContractor.costOfwork = this.inputForm.value.priceOrTotal;
      }
    }

    dialogoTranfer.oldSubContractor.itemDeliteEdit = true;
    // dialogoTranfer.oldSubContractor.costOfwork = 0;
    // dialogoTranfer.oldSubContractor.totalCost = 0;
    // dialogoTranfer.oldSubContractor.profit = 0;

    this.dialogRef.close(dialogoTranfer);
  }



  copyItem(item: Subcontractor): Subcontractor {
    const sub = new Subcontractor();

    sub.company = item.company;
    sub.phoneNumber = item.phoneNumber;
    sub.mail = item.mail;
    sub.description = item.description;
    sub.codeClient = item.codeClient;
    sub.costOfwork = 0;
    sub.totalCost = 0;
    sub.profit = item.profit;

    if (item.dateOfWork !== undefined && item.dateOfWork != null) {
      sub.dateOfWork = item.dateOfWork;
    }

    if (item.datePain !== undefined && item.datePain != null) {
      sub.datePain = item.datePain;
    }

    if (item.billListSubcontractor !== undefined && item.billListSubcontractor != null) {
      sub.billListSubcontractor = item.billListSubcontractor;
    }

    return sub;
  }


  onCancel(): void {
    this.dialogRef.close();
  }


  changePrices(): void {
    console.log(this.checked);
  }
}
