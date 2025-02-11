import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-panel-item',
  templateUrl: './panel-item.component.html',
  styleUrls: ['./panel-item.component.css']
})
export class PanelItemComponent implements OnInit {

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

  item: any;

  constructor(private fb: FormBuilder, private dialogRef: MatDialogRef<PanelItemComponent>, @Inject(MAT_DIALOG_DATA) public data?: any) {

    if (data.item !== undefined && data.item != null) {
      this.item = data.item;
    }

  }


  ngOnInit(): void {
  }


  onAgree(): void {
    this.dialogRef.close();
  }


  onCancel(): void {
    this.dialogRef.close();
  }


  changePrices(): void {
    console.log(this.checked);
  }


}

