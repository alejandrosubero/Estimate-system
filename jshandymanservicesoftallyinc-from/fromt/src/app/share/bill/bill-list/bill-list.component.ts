import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Bill } from 'src/app/models/bill.model';
import { DialogoBill } from 'src/app/models/Dialogo-bill';
import { ConfirmationDialogComponent } from '../../confirmation-dialog/confirmation-dialog.component';
import { NewBillComponent } from '../new-bill/new-bill.component';

@Component({
  selector: 'app-bill-list',
  templateUrl: './bill-list.component.html',
  styleUrls: ['./bill-list.component.css']
})
export class BillListComponent implements OnInit, OnChanges {

  // displayedColumns: string[] = ['Description', 'TotalCost', 'delete', 'edit'];
  displayedColumns: string[] = ['Description', 'TotalCost', 'actiones'];
  dataSourceBill: MatTableDataSource<Bill> = new MatTableDataSource<Bill>();
  @Input() billList: Array<Bill>;

  @Output() edidBillEvent = new EventEmitter<Bill>();
  @Output() deleteBill = new EventEmitter<Bill>();

  constructor(private dialog: MatDialog) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.updatedatasourse(this.billList);
  }

  ngOnInit(): void {
    if (this.billList !== undefined && this.billList.length > 0) {
      this.dataSourceBill.data = this.billList;
    }
  }

  updatedatasourse(bills: Array<Bill>): void {
    this.billList = bills;
    this.dataSourceBill.data = this.billList;
  }


  onAddNewBill(editar?: boolean, billRow?: any): void {

    let bill = new Bill();
    editar !== undefined && editar != null && editar ? bill = billRow : editar = false;

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '95%';
    dialogConfig.height = '85%';

    dialogConfig.data = {
      atributo: bill,
      edit: editar,
    };

    const dialogRef = this.dialog.open(NewBillComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((x: DialogoBill) => {

      if (x !== undefined) {
        const listaBill = this.billList.filter(y => y != billRow);
        this.deleteBill.emit(billRow);

        if (x.oldBill !== undefined && x.oldBill != null
          && x.oldBill.itemDeliteEdit && x.newBill === undefined || x.newBill == null) {
          listaBill.push(x.oldBill);
          this.edidBillEvent.emit(x.oldBill);
        }


        if (x.oldBill !== undefined && x.oldBill != null
          && x.oldBill.itemDeliteEdit && x.newBill !== undefined && x.newBill != null) {
          listaBill.push(x.oldBill);
          listaBill.push(x.newBill);
          this.edidBillEvent.emit(x.oldBill);
          this.edidBillEvent.emit(x.newBill);
        }

        if (x.oldBill === undefined || x.oldBill == null
          &&  x.newBill !== undefined && x.newBill != null) {
          listaBill.push(x.newBill);
          this.edidBillEvent.emit(x.newBill);
        }

        this.billList = listaBill;
        this.updatedatasourse(this.billList);
        // this.edidBillEvent.emit(x);
      }
    });
  }


  onRemove(billRow?: Bill): void {
    // const listaBill = this.billList.filter( y => y != billRow);
    // this.billList = listaBill;
    // this.updatedatasourse(this.billList);
    // this.deleteBill.emit(billRow);
    this.notificationRemove(billRow);
  }


  notificationRemove(billRow?: Bill): void {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      title: ' Table data deletion alert',
      msg: 'Are you sure you want to delete the Bill?',
      cancel: 'Cancel',
      confirm: 'Confirm'
    };

    const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(response => {
      if (response) {
        const listaBill = this.billList.filter(y => y != billRow);
        this.billList = listaBill;
        this.updatedatasourse(this.billList);
        this.deleteBill.emit(billRow);
      }
    });
  }



}
