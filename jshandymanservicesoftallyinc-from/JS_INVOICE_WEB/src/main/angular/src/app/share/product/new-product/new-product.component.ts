import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogoProduct } from 'src/app/models/dialogo-transfer-producto.model';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { Product } from 'src/app/models/product.model';
import { CoreService } from 'src/app/services/core.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { AppPanelItemProductComponent } from '../app-panel-item-product/app-panel-item-product.component';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {

  productForm = this.fb.group({
    productCode: [null, Validators.required],
    name: [null, Validators.required],
    description: [null, Validators.required],
    price: [null, Validators.required],
    stockNumber: [null, Validators.required],
    priceWithTaxes: [null],
    categories: [null],
    notas: [null],
    totalPriceWithTaxes: [null],
    totalPriceWithOupTaxes: [null],
  });


  product: Product;
  oldProduct: Product = new Product();
  titleProduct = '';
  seccion: LogingResponse;
  dialogoProduct: DialogoProduct = new DialogoProduct();
  dataEdit = false;

  constructor(
    private fb: FormBuilder,
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<NewProductComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private sessionService: SessionStorageService,
    private coreService: CoreService,) {

    if (data.edit) {
      this.dataEdit = data.edit;
      this.product =  this.newProducto(data.atributo);
      this.titleProduct = 'PRODUCT';
      this.updateView();
    } else {
      this.titleProduct = 'NEW PRODUCT';
      this.product = new Product();
      this.dialogoProduct = new DialogoProduct();
    }
    this.seccion = this.sessionService.get('UserSession');
  }


  ngOnInit(): void {
  }




  onSave(): void {

    this.product.productCode = this.productForm.value.productCode;
    this.product.name = this.productForm.value.name;
    this.product.description = this.productForm.value.description;

    if (this.productForm.value.categories !== undefined && this.productForm.value.categories != null) {
      this.product.categories = this.productForm.value.categories;
    }

    if (this.productForm.value.notas !== undefined && this.productForm.value.notas != null) {
      this.product.notas = this.productForm.value.notas;
    }

    this.product.price = this.productForm.value.price;
    this.product.priceWithTaxes = this.productForm.value.priceWithTaxes;
    this.product.stockNumber = this.productForm.value.stockNumber;
    this.product.totalPriceWithTaxes = this.productForm.value.totalPriceWithTaxes;
    this.product.totalPriceWithOupTaxes = this.productForm.value.totalPriceWithOupTaxes;


    if (this.dataEdit && this.oldProduct !== undefined && this.oldProduct != null
      && this.oldProduct.description !== undefined && this.oldProduct.description != null) {
      this.dialogoProduct.oldProduct = this.oldProduct;
    }

    this.dialogoProduct.newProduct = this.product;

    this.dialogRef.close(this.dialogoProduct);

  }



  onCancel(): void {
    this.dialogRef.close();
  }


  updateView(): void {
    this.productForm.patchValue({
      productCode: this.product.productCode,
      name: this.product.name,
      categories: this.product.categories,
      description: this.product.description,
      notas: this.product.notas,
      price: this.product.price,
      priceWithTaxes: this.product.priceWithTaxes,
      stockNumber: this.product.stockNumber,
      totalPriceWithTaxes: this.coreService.round(this.product.totalPriceWithTaxes),
      totalPriceWithOupTaxes: this.coreService.round(this.product.totalPriceWithOupTaxes)
    });
  }


  checkPrice(): void {

    if (this.productForm.value.price === undefined ||
      this.productForm.value.price == null
      && (this.productForm.value.priceWithTaxes !== undefined && this.productForm.value.priceWithTaxes != null)) {

      const valor = this.product.priceWithTaxes - (this.product.priceWithTaxes * (this.seccion.taxes / 100));
      this.productForm.patchValue({
        price: valor,
        priceWithTaxes: this.product.priceWithTaxes,
      });
      this.product.price = valor;
    }

    if (this.productForm.value.priceWithTaxes === undefined ||
      this.productForm.value.priceWithTaxes == null
      && (this.productForm.value.price !== undefined && this.productForm.value.price != null)) {

      const valorWithTaxes = this.productForm.value.price + (this.productForm.value.price * (this.seccion.taxes / 100));
      this.productForm.patchValue({
        price: this.productForm.value.price,
        priceWithTaxes: valorWithTaxes,
      });
      this.product.priceWithTaxes = valorWithTaxes;
    }


    if ((this.productForm.value.price !== undefined || this.productForm.value.price != null)
      && (this.productForm.value.priceWithTaxes !== undefined && this.productForm.value.priceWithTaxes != null)) {
      const valorWithTaxes = this.productForm.value.price + (this.productForm.value.price * (this.seccion.taxes / 100));
      this.productForm.patchValue({
        price: this.productForm.value.price,
        priceWithTaxes: valorWithTaxes,
      });
      this.product.price = this.productForm.value.price;
      this.product.priceWithTaxes = valorWithTaxes;
    }


    if (this.productForm.value.stockNumber !== undefined && this.productForm.value.stockNumber != null
      && (this.product.price !== undefined && this.product.price != null)) {

      this.product.stockNumber = this.productForm.value.stockNumber;
      const totalValor = this.product.price * this.product.stockNumber;
      this.product.totalPriceWithOupTaxes = totalValor;
      // tslint:disable-next-line: max-line-length
      this.product.totalPriceWithTaxes = this.product.totalPriceWithOupTaxes + this.product.totalPriceWithOupTaxes * (this.seccion.taxes / 100);

      this.productForm.patchValue({
        totalPriceWithTaxes: this.coreService.round(this.product.totalPriceWithTaxes),
        totalPriceWithOupTaxes: this.coreService.round(this.product.totalPriceWithOupTaxes)
      });
    }

  }


  onDescription(): void {
    this.product.description = this.product.description;
  }

  onNote(): void {
    this.product.notas = this.productForm.value.notas;
  }


  panelItem(): void {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      item: this.product,
    };

    const dialogRef = this.dialog.open(AppPanelItemProductComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((dialogoProduct: DialogoProduct) => {

      if (dialogoProduct !== undefined && dialogoProduct != null) {

        if (dialogoProduct.oldProduct.itemDeliteEdit && dialogoProduct.newProduct !== undefined && dialogoProduct.newProduct != null) {
          this.titleProduct = 'NEW PRODUCT';
          this.product = dialogoProduct.newProduct;
          this.oldProduct = dialogoProduct.oldProduct;
          this.updateView();
          this.checkPrice();
        }

        if (dialogoProduct.oldProduct.itemDeliteEdit && dialogoProduct.newProduct === undefined || dialogoProduct.newProduct == null) {
          this.titleProduct = 'PRODUCT';
          this.product = dialogoProduct.oldProduct;
          this.updateView();
          this.checkPrice();
        }

      }
    });
  }


  newProducto(item: Product): Product {
    const product = new Product();
    product.productCode = item.productCode;
    product.name = item.name;
    product.categories = item.categories;
    product.description = item.description;
    product.notas = item.notas;
    product.stockNumber = item.stockNumber;
    product.price = item.price;
    product.priceWithTaxes = item.priceWithTaxes;
    product.totalPriceWithTaxes = item.totalPriceWithTaxes;
    product.totalPriceWithOupTaxes = item.totalPriceWithOupTaxes;
    product.idBill = item.idBill;
    product.idProduct = item.idProduct;
    product.itemDeliteEdit = item.itemDeliteEdit;
    return product;
  }


 

}
