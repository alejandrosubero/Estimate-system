import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogoProduct } from 'src/app/models/dialogo-transfer-producto.model';
import { Product } from 'src/app/models/product.model';

@Component({
  selector: 'app-app-panel-item-product',
  templateUrl: './app-panel-item-product.component.html',
  styleUrls: ['./app-panel-item-product.component.css']
})
export class AppPanelItemProductComponent implements OnInit {

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

  item: Product = new Product();

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AppPanelItemProductComponent>,
    @Inject(MAT_DIALOG_DATA) public data?: any) {
    if (data.item !== undefined && data.item != null) { this.item = data.item; }
  }


  ngOnInit(): void {
  }


  onAgree(): void {

    const dialogoTranfer = new DialogoProduct();
    dialogoTranfer.oldProduct = this.item;

    if (this.checked) {
      dialogoTranfer.newProduct = this.copyItem(this.item);
      if (this.inputForm.value.priceOrTotal !== undefined && this.inputForm.value.priceOrTotal != null) {
        dialogoTranfer.newProduct.price = this.inputForm.value.priceOrTotal;
      }
    }

    dialogoTranfer.oldProduct.itemDeliteEdit = true;
    // dialogoTranfer.oldProduct.price = 0;
    // dialogoTranfer.oldProduct.priceWithTaxes = 0;
    // dialogoTranfer.oldProduct.totalPriceWithTaxes = 0;
    // dialogoTranfer.oldProduct.totalPriceWithOupTaxes = 0;

    this.dialogRef.close(dialogoTranfer);
  }


  copyItem(item: Product): Product {
    const product = new Product();
    product.productCode = item.productCode;
    product.name = item.name;
    product.categories = item.categories;
    product.description = item.description;
    product.notas = item.notas;
    product.stockNumber = item.stockNumber;

    product.price = 0;
    product.priceWithTaxes = 0;
    product.totalPriceWithTaxes = 0;
    product.totalPriceWithOupTaxes = 0;

    return product;
  }


  onCancel(): void {
    this.dialogRef.close();
  }


  changePrices(): void {
    console.log(this.checked);
  }

}
