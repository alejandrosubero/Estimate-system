import { Component, Inject, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Bill } from 'src/app/models/bill.model';
import { DialogoBill } from 'src/app/models/Dialogo-bill';
import { DialogoProduct } from 'src/app/models/dialogo-transfer-producto.model';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { Product } from 'src/app/models/product.model';
import { CoreService } from 'src/app/services/core.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { ConfirmationDialogComponent } from '../../confirmation-dialog/confirmation-dialog.component';
import { NewProductComponent } from '../../product/new-product/new-product.component';
import { AppPanelItemBillComponent } from '../app-panel-item-bill/app-panel-item-bill.component';

@Component({
  selector: 'app-new-bill',
  templateUrl: './new-bill.component.html',
  styleUrls: ['./new-bill.component.css']
})
export class NewBillComponent implements OnInit, OnChanges {

  billForm = this.fb.group({
    billNumber: [null, Validators.required],
    billType: [null, Validators.required],
    description: [null, Validators.required],
  });

  inputForm = this.fb.group({
    billTotal: [null, Validators.required],
    billTotalWichoutTaxes: [null],
  });


  displayedColumns: string[] = ['name', 'stockNumber', 'totalPriceWithTaxes', 'Actions'];
  dataSourceNewBill: MatTableDataSource<Product> = new MatTableDataSource<Product>();
  productosList: Array<Product> = new Array<Product>();

  edit = false;
  bill: Bill;
  oldBill: Bill;
  title = '';
  seccion: LogingResponse;
  dialogoBill: DialogoBill = new DialogoBill();
  
  constructor(
    public snackBar: MatSnackBar,
    private sessionService: SessionStorageService,
    private coreService: CoreService,
    private fb: FormBuilder,
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<NewBillComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {

    if (data.edit) {
      this.bill = this.newBill(data.atributo);
      this.title = 'Bill';
      this.edit = data.edit;
      this.updateView();
    } else {
      this.title = 'NEW Bill';
      this.bill = new Bill();
    }
    this.seccion = this.sessionService.get('UserSession');
  }


  ngOnInit(): void {
  }


  updateView(): void {
    this.billForm.patchValue({
      billNumber: this.bill.billNumber,
      billType: this.bill.billType,
      description: this.bill.description,
      billTotal: this.bill.billTotal,
      billTotalWichoutTaxes: this.bill.billTotalWichoutTaxes,
    });

    this.inputForm.patchValue({
      billTotal: this.bill.billTotal,
      billTotalWichoutTaxes: this.bill.billTotalWichoutTaxes,
    });

    this.updatedatasourse(this.bill.productsAndServices);
    this.checkingList();
  }

  onSave(): void {

    if (this.billForm.valid) {

      if (this.edit && this.oldBill !== undefined && this.oldBill != null) {
        this.dialogoBill.oldBill = this.oldBill;
      }

      this.bill.billNumber = this.billForm.value.billNumber;
      this.bill.billType = this.billForm.value.billType;
      this.dialogoBill.newBill = this.bill;

      this.dialogRef.close(this.dialogoBill);
    } else {
      this.snackBar.open('Fill in all required fields', 'Cerrar');
    }
  }


  onCancel(): void {
    this.dialogRef.close();
  }

  ngOnChanges(changes: SimpleChanges): void {
    // console.log('changes bill');
  }

  updatedatasourse(products: Array<Product>): void {
    this.dataSourceNewBill.data = products;
  }

  onDescription(): void {
    this.bill.description = this.billForm.value.description;
  }



  onAddOrEdidProduct(editar?: boolean, product?: Product): void {
    let produc = new Product();
    editar !== undefined && editar != null && editar ? produc = product : editar = false;

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = '90%';

    dialogConfig.data = {
      atributo: produc,
      edit: editar,
    };

    const dialogRef = this.dialog.open(NewProductComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((x: DialogoProduct) => {

      if (x !== undefined) {

        if (editar) {
          const lista = this.bill.productsAndServices.filter(y => y != product);

          if (x.oldProduct !== undefined && x.oldProduct != null && x.newProduct !== undefined && x.newProduct != null
            && x.oldProduct.itemDeliteEdit) {
            this.bill.productsAndServices = lista;
            this.bill.productsAndServices.push(x.oldProduct);
            this.bill.productsAndServices.push(x.newProduct);
          }

          if (x.newProduct !== undefined && x.newProduct != null && x.newProduct.itemDeliteEdit
            && x.oldProduct === undefined || x.oldProduct == null) {
            this.bill.productsAndServices = lista;
            this.bill.productsAndServices.push(x.newProduct);
          }

        } else {
          if (x.newProduct !== undefined || x.newProduct != null && x.oldProduct === undefined || x.oldProduct == null) {
            this.bill.productsAndServices.push(x.newProduct);
          }
        }

        this.updatedatasourse(this.bill.productsAndServices);
        this.total();
        this.checkingList();
        // if (this.bill.productsAndServices.length !== undefined && this.bill.productsAndServices.length != null
        //   && this.bill.productsAndServices.length > 0) {
        //   this.disableManualTotal();
        // }
      } else {
        this.total();
        this.checkingList();
      }
    });
  }


  onRemove(row?: Product): void {
    this.notificationRemove(row);
  }


  notificationRemove(row?: Product): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      title: ' Table data deletion alert',
      msg: 'Are you sure you want to delete the Product?',
      cancel: 'Cancel',
      confirm: 'Confirm'
    };
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(response => {
      if (response) {
        const lista = this.bill.productsAndServices.filter(y => y != row);
        this.bill.productsAndServices = lista;
        this.updatedatasourse(this.bill.productsAndServices);
        this.total();
        this.checkingList();

      }
    });
  }



  checkingList(): void {
    if (this.bill.productsAndServices !== undefined && this.bill.productsAndServices != null
      && this.bill.productsAndServices.length > 0) {
      this.disableManualTotal();
    } else {
      this.enableManualTotal();
    }
  }


  disableManualTotal(): void {
    this.inputForm.disable();
  }

  enableManualTotal(): void {
    this.inputForm.enable();
  }

  total(): void {
    this.bill.billTotalWichoutTaxes = 0;
    this.bill.billTotal = 0;

    this.bill.productsAndServices.forEach(product => {
      if (!product.itemDeliteEdit) {
        this.bill.billTotalWichoutTaxes += product.totalPriceWithOupTaxes;
        this.bill.billTotal += product.totalPriceWithTaxes;
      }
    });

    // round
    this.bill.billTotalWichoutTaxes = this.coreService.round(this.bill.billTotalWichoutTaxes);
    this.bill.billTotal = this.coreService.round(this.bill.billTotal);
 
    this.inputForm.patchValue({
      billTotalWichoutTaxes: this.bill.billTotalWichoutTaxes,
      billTotal: this.bill.billTotal
    });
  }


  totalManualWichOutTaxes(): void {
    // tslint:disable-next-line: max-line-length
    const valorWithTaxes = this.inputForm.value.billTotalWichoutTaxes + (this.inputForm.value.billTotalWichoutTaxes * (this.seccion.taxes / 100));
    const valorWithTaxesRound = this.coreService.round(valorWithTaxes);
    this.inputForm.patchValue({
      billTotal: valorWithTaxesRound,
    });

    this.bill.billTotalWichoutTaxes = this.coreService.round(this.inputForm.value.billTotalWichoutTaxes);
    this.bill.billTotal = this.inputForm.value.billTotal;
  }


  totalManualWichTaxes(): void {
    const valorWichOutTaxes = this.inputForm.value.billTotal - (this.inputForm.value.billTotal * (this.seccion.taxes / 100));
    const valorWichOutTaxesRound = this.coreService.round(valorWichOutTaxes);
    this.inputForm.patchValue({
      // billTotal: this.billForm.value.billTotal,
      billTotalWichoutTaxes: valorWichOutTaxesRound,
    });
    this.bill.billTotalWichoutTaxes = this.inputForm.value.billTotalWichoutTaxes;
    this.bill.billTotal = this.coreService.round(this.inputForm.value.billTotal);
  }


  // tslint:disable-next-line: typedef
  financial(x) {
    return Number.parseFloat(x).toFixed(2);
  }


  panelItem(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      item: this.bill,
    };

    const dialogRef = this.dialog.open(AppPanelItemBillComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((dialogo: DialogoBill) => {

      if (dialogo !== undefined && dialogo != null) {
        if (dialogo.oldBill.itemDeliteEdit && dialogo.newBill !== undefined && dialogo.newBill != null) {
          this.title = 'NEW Bill';
          this.bill = dialogo.newBill;
          this.oldBill = dialogo.oldBill;
          this.updateView();
          this.totalManualWichTaxes();
        }

        if (dialogo.oldBill.itemDeliteEdit && dialogo.newBill === undefined || dialogo.newBill == null) {
          this.title = 'Bill';
          this.bill = dialogo.oldBill;
          this.updateView();
        }
        // this.dialogoBill = dialogo;
      }
    });
  }


  newBill(item: Bill): Bill {
    const bill = new Bill();

    bill.billNumber = item.billNumber;
    bill.billType = item.billType;
    bill.description = item.description;
    bill.productsAndServices = item.productsAndServices;
    bill.billTotal = item.billTotal;
    bill.billTotalWichoutTaxes = item.billTotalWichoutTaxes;

    if (item.idBill !== undefined && item.idBill != null) { bill.idBill = item.idBill; }
    if (item.idEstimate !== undefined && item.idEstimate != null) { bill.idEstimate = item.idEstimate; }
    if (item.idSubContractor !== undefined && item.idSubContractor != null) { bill.idSubContractor = item.idSubContractor; }
    if (item.idWork !== undefined && item.idWork != null) { bill.idWork = item.idWork; }
    if (item.itemDeliteEdit !== undefined && item.itemDeliteEdit != null) { bill.itemDeliteEdit = item.itemDeliteEdit; }

    return bill;
  }


}
