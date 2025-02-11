import { Component, Inject, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Bill } from 'src/app/models/bill.model';
import { DialogoBill } from 'src/app/models/Dialogo-bill';
import { DialogoSubContractors } from 'src/app/models/Dialogo-subcontractors.model';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { Subcontractor } from 'src/app/models/subcontractor.model';
import { CoreService } from 'src/app/services/core.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { BillListComponent } from '../../bill/bill-list/bill-list.component';
import { NewBillComponent } from '../../bill/new-bill/new-bill.component';
import { AppPanelItemSubcontractorsComponent } from '../app-panel-item-subcontractors/app-panel-item-subcontractors.component';


// tslint:disable-next-line: typedef
export function emailValidator(control: AbstractControl) {
  // tslint:disable-next-line: prefer-const
  var EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
  if (!EMAIL_REGEXP.test(control.value)) {
    return { invalidEmail: true };
  }
  return null;
}

@Component({
  selector: 'app-new-sub-contractors',
  templateUrl: './new-sub-contractors.component.html',
  styleUrls: ['./new-sub-contractors.component.css']
})
export class NewSubContractorsComponent implements OnInit, OnChanges {

  subContractorsForm = this.fb.group({
    company: [null, Validators.required],
    phoneNumber: [null, Validators.required],
    mail: [null, [Validators.required, emailValidator]],
    costOfwork: [null, Validators.required],
    totalCost: [null, Validators.required],
    description: [null, Validators.required],
    codeClient: [null],
    profit: [null],
    dateOfWork: [null],
    datePain: [null],
  });


  title = '';
  seccion: LogingResponse;
  subcontractor: Subcontractor;
  oldSubcontractor: Subcontractor = new Subcontractor();
  dialogoSuSubContractors: DialogoSubContractors = new DialogoSubContractors();
  billList: Array<Bill>;
  billExist: boolean;
  edit = false;

  @ViewChild(BillListComponent) billListTablet: BillListComponent;


  constructor(
    public snackBar: MatSnackBar,
    private coreservice: CoreService,
    private fb: FormBuilder,
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<NewSubContractorsComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
    private SessionService: SessionStorageService) {

    if (data.edit) {
      this.edit = data.edit;
      this.subcontractor = this.newSubcontractor(data.atributo);
      this.title = 'Subcontractors';
      this.billExist = this.subcontractor.billListSubcontractor.length > 0 ? true : false;
      this.updateView();
    } else {
      this.title = 'NEW Subcontractors';
      this.subcontractor = new Subcontractor();
      this.billExist = this.subcontractor.billListSubcontractor.length > 0 ? true : false;
    }

    this.billList = this.subcontractor.billListSubcontractor;
    this.seccion = this.SessionService.get('UserSession');
  }


  ngOnChanges(changes: SimpleChanges): void {
    // console.log('changes NewSubContractorsComponent');
  }


  ngOnInit(): void {
  }



  totalManual(): void {
    this.subcontractor.totalCost = this.subContractorsForm.value.totalCost;
  }


  total(): void {
    this.subcontractor.totalCost = 0;
    this.subcontractor.totalCost += this.subContractorsForm.value.costOfwork;

    if (this.subcontractor.billListSubcontractor.length > 0) {

      this.subcontractor.billListSubcontractor.forEach(y => {
        if (!y.itemDeliteEdit){
          this.subcontractor.totalCost += y.billTotal;
        }
      });

      this.subcontractor.totalCost = this.coreservice.round(this.subcontractor.totalCost);
    }

    if (this.subContractorsForm.value.profit !== undefined && this.subContractorsForm.value.profit !== null) {
      this.profiAdd();
    } else {
      this.updateTotalCostOfworkInView();
    }

  }

  profiAdd(): void {
    if (this.subContractorsForm.value.profit !== undefined && this.subContractorsForm.value.profit !== null) {
      this.subcontractor.profit = this.subContractorsForm.value.profit;
      this.subcontractor.profitCalculation = this.subcontractor.totalCost * (this.subcontractor.profit / 100);
      // let total = this.subcontractor.totalCost + (this.subcontractor.totalCost * (this.subcontractor.profit / 100));
      let total = this.subcontractor.totalCost +  this.subcontractor.profitCalculation;
      this.subcontractor.totalCost = this.coreservice.round(total);
      this.updateTotalCostOfworkInView();
    }
  }

  updateTotalCostOfworkInView(): void {
    this.subContractorsForm.patchValue({
      totalCost: this.subcontractor.totalCost,
    });

  }

  updateView(): void {
    this.subContractorsForm.patchValue({
      company: this.subcontractor.company,
      phoneNumber: this.subcontractor.phoneNumber,
      mail: this.subcontractor.mail,
      costOfwork: this.subcontractor.costOfwork,
      totalCost: this.coreservice.round(this.subcontractor.totalCost),
      description: this.subcontractor.description,
      profit: this.subcontractor.profit,
      dateOfWork: this.subcontractor.dateOfWork,
      datePain: this.subcontractor.datePain,
      // codeClient:  this.subcontractor.codeClient,
    });
  }

  onSave(): void {

    if (this.subContractorsForm.valid) {

      if (this.edit && this.oldSubcontractor !== undefined && this.oldSubcontractor != null 
        && this.oldSubcontractor.description !== undefined && this.oldSubcontractor.description != null) {
        this.dialogoSuSubContractors.oldSubContractor = this.oldSubcontractor;
      }

      this.subcontractor.company = this.subContractorsForm.value.company;
      this.subcontractor.phoneNumber = this.subContractorsForm.value.phoneNumber;
      this.subcontractor.mail = this.subContractorsForm.value.mail;
      this.subcontractor.costOfwork = this.subContractorsForm.value.costOfwork;
      // this.subcontractor.totalCost = this.subContractorsForm.value.totalCost;

      if (this.subContractorsForm.value.dateOfWork !== undefined && this.subContractorsForm.value.dateOfWork !== null) {
        this.subcontractor.dateOfWork = this.subContractorsForm.value.dateOfWork;
      }

      if (this.subContractorsForm.value.datePain !== undefined && this.subContractorsForm.value.datePain !== null) {
        this.subcontractor.datePain = this.subContractorsForm.value.datePain;
      }

      if (this.subContractorsForm.value.profit !== undefined && this.subContractorsForm.value.profit !== null) {
        this.subcontractor.profit = this.subContractorsForm.value.profit;
      }

      if (this.subContractorsForm.value.codeClient !== undefined && this.subContractorsForm.value.codeClient !== null) {
        this.subcontractor.codeClient = this.subContractorsForm.value.codeClient;
      }

      // if (this.subContractorsForm.value.description !== undefined && this.subContractorsForm.value.description !== null) {
      //   this.subcontractor.description = this.subContractorsForm.value.description;
      // }

      this.dialogoSuSubContractors.newSubContractor = this.subcontractor;
      this.dialogRef.close(this.dialogoSuSubContractors);
    } else {
      this.snackBar.open('Fill in all required fields', 'Cerrar');
    }


  }


  onCancel(): void {
    this.dialogRef.close();
  }


  onAddNewBill(editar?: boolean, billRow?: any): void {

    let bill = new Bill();
    editar !== undefined && editar != null && editar ? bill = billRow : editar = false;

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = 'auto';
    dialogConfig.height = 'auto';

    dialogConfig.data = {
      atributo: bill,
      edit: editar,
    };

    const dialogRef = this.dialog.open(NewBillComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((x: DialogoBill) => {

      if (x !== undefined) {

        if (editar) {
          const listaBill = this.billList.filter(y => y != billRow);

          if (x.oldBill !== undefined && x.oldBill != null
            && x.oldBill.itemDeliteEdit && x.newBill === undefined || x.newBill == null) {
            listaBill.push(x.oldBill);
            this.subcontractor.billListSubcontractor = listaBill;
            this.total();
            this.billList = this.subcontractor.billListSubcontractor;
            this.billListTablet.updatedatasourse(this.billList);
          }

          if (x.oldBill !== undefined && x.oldBill != null
            && x.oldBill.itemDeliteEdit && x.newBill !== undefined && x.newBill != null) {
            listaBill.push(x.oldBill);
            listaBill.push(x.newBill);
            this.subcontractor.billListSubcontractor = listaBill;
            this.total();
            this.billList = this.subcontractor.billListSubcontractor;
            this.billListTablet.updatedatasourse(this.billList);
          }

        } else {
          if (x.newBill !== undefined && x.newBill != null) {
            this.subcontractor.billListSubcontractor.push(x.newBill);
            this.total();
            this.billList = this.subcontractor.billListSubcontractor;
            this.billListTablet.updatedatasourse(this.billList);
          }
        }
      }
    });

    // dialogRef.afterClosed().subscribe(x => {
    //   if (x !== undefined) {
    //     this.subcontractor.billListSubcontractor.push(x);
    //     this.total();
    //     this.billList = this.subcontractor.billListSubcontractor;
    //     this.billListTablet.updatedatasourse(this.billList);
    //   }
    // });

  }


  addbill(bill: Bill): void {
    this.subcontractor.billListSubcontractor.push(bill);
    this.total();
    this.updateView();
    // console.log('add-Bill:', this.subcontractor.billListSubcontractor);
  }

  deleteBill(bill: Bill): void {
    // tslint:disable-next-line: max-line-length
    const listaBills = this.subcontractor.billListSubcontractor.filter((x: Bill) => x !== bill);
    this.subcontractor.billListSubcontractor = listaBills;
    this.total();
    this.updateView();
  }

  onDescription(): void {
    this.subcontractor.description = this.subContractorsForm.value.description;
  }


  addPainEvent(type: string, event: MatDatepickerInputEvent<Date>): void {
    this.subcontractor.datePain = this.subContractorsForm.value.datePain;
  }


  addWorkEvent(type: string, event: MatDatepickerInputEvent<Date>): void {
    this.subcontractor.dateOfWork = this.subContractorsForm.value.dateOfWork;
  }



  panelItem(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      item: this.subcontractor,
    };

    const dialogRef = this.dialog.open(AppPanelItemSubcontractorsComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((dialogo: DialogoSubContractors) => {

      if (dialogo !== undefined && dialogo != null) {
        if (dialogo.oldSubContractor.itemDeliteEdit && dialogo.newSubContractor !== undefined && dialogo.newSubContractor != null) {

          this.billExist = this.subcontractor.billListSubcontractor.length > 0 ? true : false;
          this.title = 'NEW Subcontractors';
          this.subcontractor = new Subcontractor();
          this.subcontractor = dialogo.newSubContractor;
          this.oldSubcontractor = dialogo.oldSubContractor;
          this.updateView();
          this.total();
        }

        if (dialogo.oldSubContractor.itemDeliteEdit && dialogo.newSubContractor === undefined || dialogo.newSubContractor == null) {
          this.title = 'Subcontractors';
          this.subcontractor = dialogo.oldSubContractor;
          this.billExist = this.subcontractor.billListSubcontractor.length > 0 ? true : false;
          this.updateView();
          this.total();
        }
      }
    });
  }


  newSubcontractor(item: Subcontractor): Subcontractor {
    const sub = new Subcontractor();

    sub.company = item.company;
    sub.phoneNumber = item.phoneNumber;
    sub.mail = item.mail;
    sub.description = item.description;
    sub.codeClient = item.codeClient;
    sub.totalCost = item.totalCost;
    sub.profit = item.profit;

    if (item.idSubContractor !== undefined && item.idSubContractor != null) { sub.idSubContractor = item.idSubContractor; }
    if (item.costOfwork !== undefined && item.costOfwork != null) { sub.costOfwork = item.costOfwork; }
    if (item.itemDeliteEdit !== undefined && item.itemDeliteEdit != null) { sub.itemDeliteEdit = item.itemDeliteEdit; }
    if (item.idEstimate !== undefined && item.idEstimate != null) { sub.idEstimate = item.idEstimate; }
    if (item.dateOfWork !== undefined && item.dateOfWork != null) { sub.idSubContractor = item.idSubContractor; }
    if (item.idWork !== undefined && item.idWork != null) { sub.idWork = item.idWork; }
    if (item.dateOfWork !== undefined && item.dateOfWork != null) { sub.dateOfWork = item.dateOfWork; }
    if (item.datePain !== undefined && item.datePain != null) { sub.datePain = item.datePain; }

    if (item.billListSubcontractor !== undefined && item.billListSubcontractor != null) {
      sub.billListSubcontractor = item.billListSubcontractor;
    }
    return sub;
  }




}
