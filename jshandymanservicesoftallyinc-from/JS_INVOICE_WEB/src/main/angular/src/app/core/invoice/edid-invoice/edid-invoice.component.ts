import { Component, OnChanges, OnInit, SimpleChanges, ViewChild, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TooltipPosition } from '@angular/material/tooltip';
import { ActivatedRoute, Router } from '@angular/router';

import { Bill } from 'src/app/models/bill.model';
import { Client } from 'src/app/models/client.model';
import { DataJshandyManServices } from 'src/app/models/DataJshandyManServices.model';
import { DialogoBill } from 'src/app/models/Dialogo-bill';
import { DialogoService } from 'src/app/models/Dialogo-services.model';
import { DialogoSubContractors } from 'src/app/models/Dialogo-subcontractors.model';
import { EmailHandyManTally } from 'src/app/models/email-handy-man-tally.model';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { ServiceHandyManTally } from 'src/app/models/service-handy-man-tally.model';
import { Subcontractor } from 'src/app/models/subcontractor.model';
import { Work } from 'src/app/models/work.model';


import { BillListComponent } from 'src/app/share/bill/bill-list/bill-list.component';
import { NewBillComponent } from 'src/app/share/bill/new-bill/new-bill.component';
import { NewClienteComponent } from 'src/app/share/client/new-cliente/new-cliente.component';
import { ConfirmationDialogComponent } from 'src/app/share/confirmation-dialog/confirmation-dialog.component';
import { NewServiceComponent } from 'src/app/share/service/new-service/new-service.component';
import { ServiceListComponent } from 'src/app/share/service/service-list/service-list.component';
import { NewSubContractorsComponent } from 'src/app/share/SubContractors/new-sub-contractors/new-sub-contractors.component';
import { SubContractorsListComponent } from 'src/app/share/SubContractors/sub-contractors-list/sub-contractors-list.component';

import { CoreService } from 'src/app/services/core.service';
import { InvoiceWorkService } from 'src/app/services/invoice-work.service';
import { NewPaimentComponent } from 'src/app/share/paiment/new-paiment/new-paiment.component';
import { Payment } from 'src/app/models/payment.model';
import { PaymentListComponent } from 'src/app/share/paiment/payment-list/payment-list.component';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { AlertStatusService } from 'src/app/services/alert-status.service';
import { DashboardService } from 'src/app/services/dashboard.service';
import { EntityRespone } from 'src/app/models/entity-respone.model';
import { SpinnerOverlayService } from 'src/app/services/spinner-overlay.service';




@Component({
  selector: 'app-edid-invoice',
  templateUrl: './edid-invoice.component.html',
  styleUrls: ['./edid-invoice.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class EdidInvoiceComponent implements OnInit, OnChanges {

  @ViewChild(ServiceListComponent) serviceTablet: ServiceListComponent;
  @ViewChild(SubContractorsListComponent) subContractorsTablet: SubContractorsListComponent;
  @ViewChild(BillListComponent) billListTablet: BillListComponent;
  @ViewChild(PaymentListComponent) paymentTablet: PaymentListComponent;

  tileAndForm = this.fb.group({
    title: ['', Validators.required],
    description: ['', Validators.required],
    starDate: ['', Validators.required],
    overhead: null,
    dedline: ['', Validators.required],
    daysToDeline: [{ value: null, disabled: true }],
  });



  inputForm = this.fb.group({
    totalCostWork: [null, Validators.required],
    totalCostWorkWithoutTaxes: [null],
    totalAmountPaind: [null],
    remainingPayable: [null],
  });

  selected = new FormControl(0);

  colorTab = 'accent';
  fondo = 'primary';
  nameCliente = '____________';
  addressCliente = '____________';
  newClientTitle = `New Client: `;
  vistaName = '';
  overheadSinbolo = ` %`;
  tooltipStatus = 'This is the Status of the Invoice';
  disableStatusList = false;
  statusList: Array<string> = ['IN PROGRESS', 'CANCELED', 'PAUSE', 'FINALIZED'];
  statusList2: Array<string> = ['APPROVED', 'IN PROGRESS', 'SEND', 'CANCELED', 'PAUSE'];

  paymentlist: Array<Payment> = new Array<Payment>();
  services: Array<ServiceHandyManTally>;
  bills: Array<Bill>;
  subcontractors: Array<Subcontractor>;

  positionOptions: TooltipPosition[] = ['below', 'above', 'left', 'right'];
  position = new FormControl(this.positionOptions[2]);
  hoveredDivId = 1;

  clinetExist: Boolean;
  billExist: boolean;
  subcontractorsExist: boolean;
  servicesExist: boolean;
  paymentExist: boolean;
  buttonView = false;
  disabledTab = true;
  disabledAvanceTab = true;
  activateManual = true;
  notificationresponse = false;
  daysToDeline = false;

  totalMnual: number = 0;
  servicesTotalOverhead: number = 0;
  billTotalOverhead: number = 0;
  billTotalWichoutTaxesOverhead: number = 0;
  subcontractorsTotalProfit: number = 0;
  servicesTotal = 0;
  billLastTotal = 0;
  subcontractorsTotal = 0;

  email: EmailHandyManTally = new EmailHandyManTally();
  dataJshandyManServices: DataJshandyManServices = new DataJshandyManServices();
  seccion: LogingResponse;
  work: Work = new Work();

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private invoiceWorkService: InvoiceWorkService,
    private coreService: CoreService,
    private dialog: MatDialog,
    private _snackBar: MatSnackBar,
    private router: Router,
    private sessionService: SessionStorageService,
    private alertStatusService: AlertStatusService,
    private dashboardService: DashboardService,
    private spinnerService: SpinnerOverlayService
  ) {
    this.seccion = this.sessionService.get('UserSession');
  }

  selectChange(event): void {
    this.selected.setValue(event);
  }


  onPrintProduct(): void {
    this.email.printProduct = this.email.printProduct ? false : true;
  }

  onViewAvancePayments(): void {
    this.email.avancePayments = this.email.avancePayments ? false : true;
  }

  ngOnChanges(changes: SimpleChanges): void {
    // console.log(changes);
  }

  getDataMail(): void {
    this.dataJshandyManServices = this.coreService.dataJshandy;
    this.email.subject = this.dataJshandyManServices.companyName;
  }

  ngOnInit(): void {
    this.getDataMail();
    this.route.queryParams.forEach(params => {
      this.billExist = false;
      this.subcontractorsExist = false;
      this.servicesExist = false;
      this.paymentExist = false;
      if (params.id !== undefined || params.id != null) {
        this.invoiceWorkService.getWorkById(params.id).subscribe((x) => {
          this.work = x.entidades[0];
          // console.log(' x.entidades[0]', x.entidades[0]);
          // console.log('Json: =>', JSON.stringify(this.work));
          this.viewWork();
        });
      } else {
        this.newView();
      }
    });
  }

  changueStatus(status: string): void {
    if (status === this.statusList[3] && this.work.payments !== undefined && this.work.payments.length <= 0) {
      this.alertStatusService.checkSattusFinal(status);
      return;
    }
    this.work.status = status;
    this.save();
  }


  newView(): void {
    this.work = new Work();
    this.vistaName = `New Invoice`;
    this.clinetExist = false;
    this.disabledTab = true;

    if (this.work.daysToDeline !== undefined && this.work.daysToDeline != null) {
      this.daysToDeline = true;
    }

    this.tileAndForm.patchValue({
      overhead: '',
      description: '',
      title: '',
      starDate: '',
      dedline: '',
      daysToDeline: this.work.daysToDeline,
    });
    this.setTabletsAtributte(this.work);


  }


  viewWork(): void {
    if (this.work !== undefined && this.work !== null) {
      if (this.work.idWork !== undefined && this.work.idWork != null
        && this.work.codeWork !== undefined && this.work.codeWork !== null) {
        this.disabledTab = false;
      }

      this.paymentAvanceViewTab();

      this.vistaName = `Invoice : ${this.work.idWork}`;
      if (this.work.client !== undefined && this.work.client != null) {
        this.sedDataClientInView(this.work.client.name, this.work.client.fullAdress);
      }
      this.sedDataInView();
      this.clinetExist = true;
      this.setTabletsAtributte(this.work);
    }
  }


  sedDataInView(): void {
    if (this.work.daysToDeline !== undefined && this.work.daysToDeline != null) {
      this.daysToDeline = true;
    }

    this.tileAndForm.patchValue({
      overhead: this.work.overhead,
      description: this.work.description,
      title: this.work.title,
      starDate: this.work.starDate,
      dedline: this.work.dedline,
      daysToDeline: this.work.daysToDeline,
    });

    this.inputForm.patchValue({
      totalCostWork: this.coreService.round(this.work.totalCostWork),
      totalCostWorkWithoutTaxes: this.coreService.round(this.work.totalCostWorkWithoutTaxes),
      totalAmountPaind: this.coreService.round(this.work.totalAmountPaind),
      remainingPayable: this.coreService.round(this.work.remainingPayable),
    });
  }


  setTablets(work: Work): void {
    this.subcontractorsExist = true;
    this.billExist = true;
    this.servicesExist = true;
    this.paymentExist = true;
    this.services = work.services;
    this.bills = work.bills;
    this.subcontractors = work.subcontractors;
    this.paymentlist = work.payments;
  }


  setTabletsAtributte(work: Work): void {
    this.setTablets(work);
    if (work.services.length > 0) { this.disableManualTotal(); }
    if (work.bills.length > 0) { this.disableManualTotal(); }
    if (work.subcontractors !== undefined && work.subcontractors.length > 0) { this.disableManualTotal(); }
    this.total();
  }


  addEvent(type: string, event: MatDatepickerInputEvent<Date>): void {
    this.work.starDate = this.tileAndForm.value.starDate;
  }

  addDedlineEvent(type: string, event: MatDatepickerInputEvent<Date>): void {
    this.work.dedline = this.tileAndForm.value.dedline;
    this.work.daysToDeline = this.calculateDedline();
    const dates = Math.round(Math.abs(this.calculateDedline()));
    this.tileAndForm.patchValue({
      daysToDeline: dates,
    });
  }



  calculateDedline(): number {
    let dif = 0;
    // tslint:disable-next-line: max-line-length
    if ((this.work.starDate != null && this.work.starDate !== undefined) && (this.work.dedline != null && this.work.dedline !== undefined)) {
      const starDate = new Date().getTime();
      const dedline = new Date(this.work.dedline).getTime();
      dif = ((dedline - starDate) / (1000 * 60 * 60 * 24));
    }
    return dif;
  }


  onTitle(): void {
    this.work.title = this.tileAndForm.value.title;
  }

  onDescription(): void {
    this.work.description = this.tileAndForm.value.description;
  }

  sedDataClientInView(name: string, address: string): void {
    this.nameCliente = ` ${name}`;
    this.addressCliente = ` ${address}`;
  }


  clearInput(): void {
    this.tileAndForm.get('title').setValue('');
  }


  onAddNewClient(editar?: boolean): void {
    let clients = new Client();
    editar !== undefined && editar != null && editar ? clients = this.work.client : editar = false;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.maxWidth = '100vw',
      dialogConfig.maxHeight = '100vh',
      dialogConfig.width = '750px',
      dialogConfig.height = '85vh',
      dialogConfig.data = {
        atributo: clients,
        edit: editar,
      };

    const dialogRef = this.dialog.open(NewClienteComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(x => {
      if (x !== undefined) {
        this.work.client = x;
        const name = `${this.work.client.name} ${this.work.client.lastName}`;
        this.sedDataClientInView(name, this.work.client.fullAdress);
        this.clinetExist = true;
      }
    });
  }

  onDeleteClient(): void {
    this.work.client = null;
    this.work.client = new Client();
    this.clinetExist = false;
  }


  onAddNewSubcontractors(editar?: boolean, subcontractorRow?: any): void {
    let service = new Subcontractor();
    editar !== undefined && editar != null && editar ? service = subcontractorRow : editar = false;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.maxWidth = '100vw',
      dialogConfig.maxHeight = '100vh',
      dialogConfig.width = '750px',
      dialogConfig.height = '85vh',

      dialogConfig.data = {
        atributo: service,
        edit: editar,
      };
    const dialogRef = this.dialog.open(NewSubContractorsComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((sub: DialogoSubContractors) => {

      if (sub !== undefined) {
        this.subcontractorsExist = true;

        if (sub.newSubContractor !== undefined && sub.newSubContractor != null) {

          if (this.work.client !== undefined && this.work.client != null) {
            sub.newSubContractor.codeClient = this.work.client.codeClient;
          }

          this.work.subcontractors.push(sub.newSubContractor);
          this.subcontractors = this.work.subcontractors;
          this.subContractorsTablet.updatedatasourse(this.work.subcontractors);
          this.total();
        }
      }
    });
  }


  addSubcontractor(subcontractor: Subcontractor): void {
    this.work.subcontractors.push(subcontractor);
    this.total();
  }


  deleteSubcontractor(subcontractor: Subcontractor): void {
    const listaSubcontractor = this.work.subcontractors.filter((y: Subcontractor) => y !== subcontractor);
    this.work.subcontractors = listaSubcontractor;
    this.checkListForEnableManualTotalInput();
  }



  onAddNewService(editar?: boolean, serviceRow?: any): void {

    let service = new ServiceHandyManTally();
    editar !== undefined && editar != null && editar ? service = serviceRow : editar = false;

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    // dialogConfig.width = 'auto';
    // dialogConfig.height = 'auto';
    dialogConfig.maxWidth = '100vw',
      dialogConfig.maxHeight = '100vh',
      dialogConfig.width = '750px',
      dialogConfig.height = '45vh',

      dialogConfig.data = {
        atributo: service,
        edit: editar,
      };

    const dialogRef = this.dialog.open(NewServiceComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((x: DialogoService) => {

      if (x !== undefined) {
        this.servicesExist = true;
        if (x.newService !== undefined && x.newService != null) {
          this.work.services.push(x.newService);
          this.serviceTablet.updatedataSourse(this.work.services);
        }
      }
      this.total();
    });
  }


  addService(service: ServiceHandyManTally): void {
    this.work.services.push(service);
    this.total();
  }

  deleteService(service: ServiceHandyManTally): void {
    // tslint:disable-next-line: max-line-length
    const listaServicios = this.work.services.filter((x: ServiceHandyManTally) => x !== service);
    this.work.services = listaServicios;
    this.checkListForEnableManualTotalInput();
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
        if (x.newBill !== undefined && x.newBill != null) {
          this.billExist = true;
          this.work.bills.push(x.newBill);
          this.bills = this.work.bills;
          this.billListTablet.updatedatasourse(this.bills);
          this.total();
        }
      }
    });
  }


  addbill(bill: Bill): void {
    this.work.bills.push(bill);
    this.total();
  }

  deleteBill(bills: Bill): void {
    // tslint:disable-next-line: max-line-length
    const listaBills = this.work.bills.filter((x: Bill) => x !== bills);
    this.work.bills = listaBills;
    this.checkListForEnableManualTotalInput();
  }


  total(): void {
    let billTotal = 0;
    let billTotalWichoutTaxes = 0;
    let servicesTotal = 0;
    let total = 0;
    let totalWichtTaxes = 0;
    let subcontractorsTotalCostOfwork = 0;
    let billWichoutTaxes = 0;

    this.servicesTotal = 0;
    this.subcontractorsTotal = 0;
    this.billLastTotal = 0;
    this.subcontractorsTotalProfit = 0;
    let totalAmountPainds = 0.0;


    // Bill...
    if (this.work.bills != null && this.work.bills !== undefined && this.work.bills.length > 0) {
      this.work.bills.forEach(bill => {
        if (!bill.itemDeliteEdit) {
          billTotal += bill.billTotal;
          billTotalWichoutTaxes += bill.billTotalWichoutTaxes;
        }
      });
      this.disableManualTotal();
    }
    this.billTotalOverhead = this.calculateOverheads(billTotal);
    this.billTotalWichoutTaxesOverhead = this.calculateOverheads(billTotalWichoutTaxes);
    billWichoutTaxes = billTotalWichoutTaxes;
    this.billLastTotal = billTotal;

    // services...
    if (this.work.services != null && this.work.services !== undefined && this.work.services.length > 0) {
      this.work.services.forEach(service => {
        if (!service.itemDeliteEdit) {
          servicesTotal += service.servicesCost;
        }
      });
      this.disableManualTotal();
      this.servicesTotalOverhead = this.calculateOverheads(servicesTotal);
      this.servicesTotal = servicesTotal;
    }


    // subcontractors...
    if (this.work.subcontractors != null && this.work.subcontractors !== undefined && this.work.subcontractors.length > 0) {
      let totalcostForProfitCalculation = 0;
      this.work.subcontractors.forEach(subcontractors => {
        if (!subcontractors.itemDeliteEdit) {
          this.subcontractorsTotal += subcontractors.totalCost;
          subcontractorsTotalCostOfwork += subcontractors.costOfwork;

          if (subcontractors.billListSubcontractor !== undefined && subcontractors.billListSubcontractor != null
            && subcontractors.billListSubcontractor.length > 0) {
            subcontractors.billListSubcontractor.forEach(billsub => {
              totalcostForProfitCalculation += billsub.billTotal;
            });
          }
          subcontractors.profitCalculation = (subcontractors.costOfwork + totalcostForProfitCalculation) * (subcontractors.profit / 100);
          this.subcontractorsTotalProfit += this.coreService.round(subcontractors.profitCalculation);
        }
      });
      this.disableManualTotal();
    }

    // tslint:disable-next-line: max-line-length
    total = this.servicesTotal + billWichoutTaxes + this.subcontractorsTotal + this.servicesTotalOverhead + this.billTotalWichoutTaxesOverhead;
    // tslint:disable-next-line: max-line-length
    totalWichtTaxes = this.servicesTotal + this.subcontractorsTotal + this.billLastTotal + this.servicesTotalOverhead + this.billTotalOverhead;
    const roumWichOutTaxes = this.coreService.round(total);
    const roumWithTaxes = this.coreService.round(totalWichtTaxes);
    this.work.totalCostWork = roumWithTaxes;
    this.work.totalCostWorkWithoutTaxes = roumWichOutTaxes;

    // payments
    if (this.work.totalAmountPaind === undefined || this.work.totalAmountPaind == null) {
      this.work.totalAmountPaind = 0;
    }
    if (this.work.payments.length > 0) {
      this.work.payments.forEach(x => {
        totalAmountPainds += x.amountPaind;
        this.work.totalAmountPaind = this.coreService.round(totalAmountPainds);
        this.work.remainingPayable = this.coreService.round(this.work.totalCostWork - this.work.totalAmountPaind);
      });
    } else {
      this.work.totalAmountPaind = 0;
    }

    this.inputForm.patchValue({
      totalCostWorkWithoutTaxes: roumWichOutTaxes,
      totalCostWork: roumWithTaxes,
      totalAmountPaind: this.work.totalAmountPaind,
      remainingPayable: this.work.remainingPayable,
    });
  }

  calculateProfit(valor: number, profit: number): number {
    let resultado: number;
    if (profit !== undefined && profit != null) {
      resultado = valor * (profit / 100);
      return this.coreService.round(resultado);
    } else {
      return this.coreService.round(valor);
    }
  }

  calculateOverheads(valor: number): number {
    let resultado: number;
    if (this.tileAndForm.value.overhead !== undefined && this.tileAndForm.value.overhead != null) {
      resultado = (valor * (this.tileAndForm.value.overhead / 100));
      return this.coreService.round(resultado);
    } else {
      return this.coreService.round(valor);
    }
  }

  calculateOverhead(valor: number): number {
    let resultado: number;
    if (this.tileAndForm.value.overhead !== undefined && this.tileAndForm.value.overhead != null) {
      resultado = valor + (valor * (this.tileAndForm.value.overhead / 100));
      return resultado;
    } else {
      return valor;
    }
  }

  totalWithTaxesCalculation(valor: number, calcula: boolean): void {
    let valorWithTaxes = 0;
    if (calcula) {
      valorWithTaxes = valor + (valor * (this.seccion.taxes / 100));
    } else {
      valorWithTaxes = valor;
    }

    const roumWithTaxes = this.coreService.round(valorWithTaxes);
    this.inputForm.patchValue({
      totalCostWork: roumWithTaxes,
    });
    this.work.totalCostWork = roumWithTaxes;
  }


  totalWichOutTaxesCalculation(valor: number, calcula: boolean): void {
    let valorWichOutTaxes = 0;
    if (calcula) {
      valorWichOutTaxes = valor - (valor * (this.seccion.taxes / 100));
    } else {
      valorWichOutTaxes = valor;
    }

    const roumWichOutTaxes = this.coreService.round(valorWichOutTaxes);
    this.inputForm.patchValue({
      totalCostWorkWithoutTaxes: roumWichOutTaxes,
    });
    this.work.totalCostWorkWithoutTaxes = roumWichOutTaxes;
  }


  totalManualWichTaxes(): void {
    let totalValorWithOverhead = 0;

    if (this.work.overhead !== undefined && this.work.overhead != null) {
      totalValorWithOverhead = this.calculateOverhead(this.inputForm.value.totalCostWork);
    } else {
      totalValorWithOverhead = this.inputForm.value.totalCostWork;
    }

    this.totalWithTaxesCalculation(totalValorWithOverhead, false);
    this.totalWichOutTaxesCalculation(totalValorWithOverhead, true);
    this.totalMnual = totalValorWithOverhead;
  }


  totalManualWichOutTaxes(): void {
    let totalValorWithOverhead = 0;
    if (this.work.overhead !== undefined && this.work.overhead != null) {
      totalValorWithOverhead = this.calculateOverhead(this.inputForm.value.totalCostWorkWithoutTaxes);
    } else {
      totalValorWithOverhead = this.inputForm.value.totalCostWorkWithoutTaxes;
    }

    this.totalWichOutTaxesCalculation(totalValorWithOverhead, false);
    this.totalWithTaxesCalculation(totalValorWithOverhead, true);
    this.totalMnual = totalValorWithOverhead;
  }

  onOverhead(): void {
    this.work.overhead = this.tileAndForm.value.overhead;
    this.total();

    if (this.inputForm.value.totalCostWork == 0 && this.totalMnual !== 0) {
      this.totalWithTaxesCalculation(this.totalMnual, false);
      this.totalWichOutTaxesCalculation(this.totalMnual, true);
    }
  }

  disableManualTotal(): void {
    this.inputForm.disable();
  }

  enableManualTotal(): void {
    this.inputForm.enable();
  }

  checkListForEnableManualTotalInput(): void {
    if (this.work.bills.length == 0 && this.work.services.length == 0 && this.work.subcontractors.length == 0) {
      this.total();
      this.enableManualTotal();
    }
  }

  saveNotification(): void {
    if (this.tileAndForm.valid) {

      let mensaje = 'Confirm that you want to save the Invoice?';
      let titles = 'Save Alert';

      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = '45%';
      dialogConfig.height = 'auto';

      if (this.work.status === 'FINALIZED' || this.work.status === 'CANCELED' || this.work.status === 'PAUSE') {
        let mensaje = `"An ${this.work.status} Invoice cannot be saved. You must change the status to be able to save."`;
        dialogConfig.data = { title: titles, msg: mensaje, cancel: 'Cancel' };
      } else {
        dialogConfig.data = { title: titles, msg: mensaje, cancel: 'Cancel', confirm: 'Confirm' };
      }

      const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
      dialogRef.afterClosed().subscribe(response => {
        if (response) {
          this.save();
        }
      });
    } else {
      this.recipientNotification();
    }
  }


  findEmptyField(): string {

    let texto = '';
    if (this.work.starDate == null || this.work.starDate === undefined) {
      texto = `${this.addAndToText(texto)} You need add the star Date`
    }
    if (this.work.dedline == null || this.work.dedline === undefined) {
      texto = `${this.addAndToText(texto)} Add Days To Dedline`
    }
    if (this.work.description == null || this.work.description === undefined) {
      texto = `${this.addAndToText(texto)} You need add Description `
    }
    if (this.work.title == null || this.work.title === undefined) {
      texto = `${this.addAndToText(texto)} You need add Title `
    }
    return texto;
  }

  addAndToText(txt: string): string {
    let newTexto = txt != '' ? `${txt} and` : txt;
    return newTexto;
  }


  recipientNotification(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      title: ' Data Alert',
      msg: `The Invoice cant not be save, you need complete all information: ${this.findEmptyField()}`,
      confirm: 'OK'
    };
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(response => {
      if (response) {
        return;
      }
    });
  }


  save(): void {
    this.show();
    if (this.work.idWork !== undefined && this.work.codeWork !== null) {
      this.invoiceWorkService.saveAndUpdateWork(this.work).subscribe((response: Work) => {
        this.work = response;
        this.getDashboardData();
        this.viewWork();
        this.hide();
      }, error => {
        this.hide();
        console.log('Error', error);
        const notification = '"An error occurred while trying to Update Invoice"';
        this.openSnackBar(notification, 'Close');
      });

    } else {
      this.invoiceWorkService.saveWork(this.work).subscribe(response => {
        this.work = response;
        this.getDashboardData();
        this.viewWork();
        this.hide();
      }, error => {
        this.hide();
        console.log('Error', error);
        const notification = '"An error occurred while trying to save a new Invoice"';
        this.openSnackBar(notification, 'Close');
      }
      );
    }
  }

  getDashboardData(): void {
    this.dashboardService.getDashboardData().subscribe((x: EntityRespone) => {
      this.dashboardService.updatedDashboardData(x.entidades[0]);
    }, (error: any) => {
      const notification = '"FAILL THE RESPONSE OFF SERVER"';
      this.openSnackBar(notification, 'Close');
    });
  }

  show(): void {
    this.spinnerService.spinnerActive = false;
    this.spinnerService.show();
  }

  hide(): void {
    this.spinnerService.hide();
    this.spinnerService.spinnerActive = true;
  }

  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
  }


  onCancel(): void {
    this.tileAndForm.patchValue({
      overhead: null,
      description: null,
      title: null,
      starDate: null,
    });
    this.inputForm.patchValue({
      totalCostWork: null,
      totalCostWorkWithoutTaxes: null,
    });
    this.work = null;
    this.router.navigateByUrl('/jshandy-man-services/menu/home');
  }


  onDeleteWork(): void {
    if (this.work.idWork !== undefined && this.work.idWork != null) {
      const mensg = 'If you want to delete the work o invoice, by accepting you will lose access to this information permanently.';
      this.notification('Delete  Work o Invoice Confirmation', mensg, 'delete', 'Cancel', 'Agree');
    } else {
      const notification = '"An  Work o Invoice cannot be convert without being Saved first"';
      this.openSnackBar(notification, 'Close');
    }
  }

  notification(titles: string, mensaje: string, action: string, cancels: string, confirms: string): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';
    dialogConfig.data = { title: titles, msg: mensaje, cancel: cancels, confirm: confirms };
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((response: boolean) => {
      if (response) {
        if (action === 'delete') { this.deleteWork(); }
      }
    });
  }


  deleteWork(): void {
    this.invoiceWorkService.deleteWork(this.work.idWork.toString()).subscribe(response => {
      const notification = response ? '"the Invoice was deleted"' : '"the Invoice cant not be delete"';
      this.openSnackBar(notification, 'Close');
      if (response) {
        this.router.navigateByUrl('/jshandy-man-services/menu/home');
      } else {
        return;
      }
    }, error => {
      console.log('Error', error);
      const notification = '"An error occurred while trying to delete Invoice"';
      this.openSnackBar(notification, 'Close');
    });
  }

  cambio(): void {
    this.buttonView = this.buttonView ? false : true;
  }



  onAddNewPayments(editar?: boolean, row?: any): void {
    let payment = new Payment();
    editar !== undefined && editar != null && editar ? payment = row : editar = false;

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = 'auto';
    dialogConfig.height = 'auto';
    dialogConfig.data = {
      atributo: payment,
      edit: editar,
    };

    const dialogRef = this.dialog.open(NewPaimentComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((x: Payment) => {
      if (x !== undefined) {
        this.paymentExist = true;
        this.work.payments.push(x);
        this.paymentTablet.updatedataSourse(this.work.payments);
        this.paymentAvanceViewTab();
      }
      this.total();
    });
  }

  addPayment(payment: Payment): void {
    this.work.payments.push(payment);
    this.total();
  }

  deletePayment(payment: Payment): void {
    const listaPayment = this.work.payments.filter((x: Payment) => x !== payment);
    this.work.payments = listaPayment;
    this.checkListForEnableManualTotalInput();
  }

  paymentAvanceViewTab(): void {

    if (this.work.idWork !== undefined && this.work.idWork != null
      && this.work.payments !== undefined && this.work.payments !== null && this.work.payments.length > 0) {
      this.disabledAvanceTab = false;
    }
  }


  showDivWithHoverStyles(divId: number): void {
    this.hoveredDivId = divId;
  }

  showAllDivsWithDefaultStyles(): void {
    this.hoveredDivId = 1;
  }

}


    // console.log('-------------------Original----------------------');
    // console.log(this.calculateDedline());

    // console.log('------------------deadLineAlert-----------------------');
    // console.log(this.dataJshandyManServices.deadLineAlert);
    // console.log('------------------deadLineAlert Calculate-----------------------');
    // console.log((this.calculateDedline() < this.dataJshandyManServices.deadLineAlert));

    // console.log('----------------------Math.round-------------------');
    // console.log(Math.round(Math.abs(this.calculateDedline())));