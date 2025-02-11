import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogoService } from 'src/app/models/Dialogo-services.model';
import { ServiceHandyManTally } from 'src/app/models/service-handy-man-tally.model';

@Component({
  selector: 'app-app-panel-item-service',
  templateUrl: './app-panel-item-service.component.html',
  styleUrls: ['./app-panel-item-service.component.css']
})
export class AppPanelItemServiceComponent implements OnInit {

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

  item: ServiceHandyManTally = new ServiceHandyManTally();

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AppPanelItemServiceComponent>,
    @Inject(MAT_DIALOG_DATA) public data?: any) {
    if (data.item !== undefined && data.item != null) { this.item = data.item; }
  }

  ngOnInit(): void {
  }

  onAgree(): void {
    const dialogoTranfer = new DialogoService();
    dialogoTranfer.oldService = this.item;
    dialogoTranfer.oldService.itemDeliteEdit = true;

    if (this.checked) {
      dialogoTranfer.newService = this.copyItem(this.item);
      if (this.inputForm.value.priceOrTotal !== undefined && this.inputForm.value.priceOrTotal != null) {
        dialogoTranfer.newService.servicesCost = this.inputForm.value.priceOrTotal;
      }
    }

    // dialogoTranfer.oldService.servicesCost = 0;
    this.dialogRef.close(dialogoTranfer);
  }


  copyItem(item: ServiceHandyManTally): ServiceHandyManTally {
    const service = new ServiceHandyManTally();
    service.servicesCost = 0;
    service.descriptionOfServicesCost = item.descriptionOfServicesCost;
    return service;
  }


  onCancel(): void {
    this.dialogRef.close();
  }


  changePrices(): void {
    console.log(this.checked);
  }
}
