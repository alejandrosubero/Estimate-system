import { Component, OnChanges, OnInit, SimpleChanges, ViewChild, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TooltipPosition } from '@angular/material/tooltip';
import { ActivatedRoute, Router } from '@angular/router';
import { AppSettings } from 'src/app/models/app-const.model';
import { Bill } from 'src/app/models/bill.model';
import { Client } from 'src/app/models/client.model';
import { DataJshandyManServices } from 'src/app/models/DataJshandyManServices.model';
import { DialogoBill } from 'src/app/models/Dialogo-bill';
import { DialogoService } from 'src/app/models/Dialogo-services.model';
import { DialogoSubContractors } from 'src/app/models/Dialogo-subcontractors.model';
import { EmailHandyManTally } from 'src/app/models/email-handy-man-tally.model';
import { EntityRespone } from 'src/app/models/entity-respone.model';
import { Estimate } from 'src/app/models/estimate.model';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { ServiceHandyManTally } from 'src/app/models/service-handy-man-tally.model';
import { Subcontractor } from 'src/app/models/subcontractor.model';
import { Work } from 'src/app/models/work.model';
import { CoreService } from 'src/app/services/core.service';
import { EstimateService } from 'src/app/services/estimate.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { SpinnerOverlayService } from 'src/app/services/spinner-overlay.service';
import { BillListComponent } from 'src/app/share/bill/bill-list/bill-list.component';
import { NewBillComponent } from 'src/app/share/bill/new-bill/new-bill.component';
import { NewClienteComponent } from 'src/app/share/client/new-cliente/new-cliente.component';
import { ConfirmationDialogComponent } from 'src/app/share/confirmation-dialog/confirmation-dialog.component';
import { NewServiceComponent } from 'src/app/share/service/new-service/new-service.component';
import { ServiceListComponent } from 'src/app/share/service/service-list/service-list.component';
import { NewSubContractorsComponent } from 'src/app/share/SubContractors/new-sub-contractors/new-sub-contractors.component';
import { SubContractorsListComponent } from 'src/app/share/SubContractors/sub-contractors-list/sub-contractors-list.component';
// import { ServiceListComponent } from 'src/app/share/service/service-list/service-list.component';


@Component({
  selector: 'app-edit-estimate',
  templateUrl: './edit-estimate.component.html',
  styleUrls: ['./edit-estimate.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class EditEstimateComponent implements OnInit, OnChanges {

  @ViewChild(ServiceListComponent) serviceTablet: ServiceListComponent;
  @ViewChild(SubContractorsListComponent) subContractorsTablet: SubContractorsListComponent;
  @ViewChild(BillListComponent) billListTablet: BillListComponent;




  tileAndForm = this.fb.group({
    title: ['', Validators.required],
    description: ['', Validators.required],
    starDate: null,
    overhead: null,
  });


  inputForm = this.fb.group({
    totalCostWork: [null, Validators.required],
    totalCostWorkWithoutTaxes: [null],
  });


  selected = new FormControl(0);

  colorTab = 'accent';
  fondo = 'primary';

  buttonView = false;
  disabledTab = true;
  nameCliente = '____________';
  addressCliente = '____________';
  newClientTitle = `New Client: `;
  vistaName: string = '';
  estimate: Estimate = new Estimate();
  services: Array<ServiceHandyManTally>;
  bills: Array<Bill>;
  subcontractors: Array<Subcontractor>;

  clinetExist: Boolean;
  billExist: boolean;
  subcontractorsExist: boolean;
  servicesExist: boolean;
  seccion: LogingResponse;
  totalMnual = 0;
  activateManual: boolean = true;


  servicesTotalOverhead: number = 0;
  billTotalOverhead: number = 0;
  billTotalWichoutTaxesOverhead: number = 0;
  subcontractorsTotalProfit: number = 0;
  overheadSinbolo = ` %`;
  disableStatusList = false;
  statusList: Array<string> = ['IN PROGRESS', 'CANCELED', 'PAUSE', 'APPROVED'];
  statusList2: Array<string> = ['APPROVED', 'IN PROGRESS', 'SEND', 'CANCELED', 'PAUSE'];

  positionOptions: TooltipPosition[] = ['below', 'above', 'left', 'right'];
  position = new FormControl(this.positionOptions[2]);
  hoveredDivId = 1;

  notificationresponse = false;
  tooltipStatus = 'This is the Status of the Estimate';
  email: EmailHandyManTally = new EmailHandyManTally();

  servicesTotal = 0;
  billLastTotal = 0;
  subcontractorsTotal = 0;
  dataJshandyManServices: DataJshandyManServices = new DataJshandyManServices();

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private serviceEstimate: EstimateService,
    private coreService: CoreService,
    private dialog: MatDialog,
    private _snackBar: MatSnackBar,
    private router: Router,
    private sessionService: SessionStorageService,
    private spinnerService: SpinnerOverlayService) {
    this.seccion = this.sessionService.get('UserSession');
  }


  selectChange(event): void {
    this.selected.setValue(event);
    // if(this.estimate.status != this.statusList[3]){
    //   this.estimate.status = this.statusList2[2];
    // }
  }


  onPrintProduct(): void {
    this.email.printProduct = this.email.printProduct ? false : true;
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
      if (params.id !== undefined || params.id != null) {
        this.serviceEstimate.getEsimateById(params.id).subscribe((x) => {
          this.estimate = x.entidades[0];
          this.viewEstimate();
        });
      } else {
        this.newEstimateview();
      }
    });
    this.mostrarTotalCost();
  }

  newEstimateview(): void {
    this.estimate = new Estimate();
    this.vistaName = `New Estimate`;
    this.disableStatusList = true;
    this.estimate.status = AppSettings.NewESTIMATE;
    this.clinetExist = false;
    this.disabledTab = true;

    this.tileAndForm.patchValue({
      overhead: '',
      description: '',
      title: '',
      starDate: '',
    });
    this.setTabletsAtributte(this.estimate);
  }


  viewEstimate(): void {
    if (this.estimate !== undefined && this.estimate !== null) {
      if (this.estimate.idEstimate !== undefined && this.estimate.idEstimate != null
        && this.estimate.codeWork !== undefined && this.estimate.codeWork !== null) {
        this.disabledTab = false;
        this.disableStatusList = false;
        this.tooltipStatus = 'This is the Status of the Estimate, click on it to change it manually.';
      }
      this.vistaName = `Estimate: ${this.estimate.idEstimate}`;
      if (this.estimate.client !== undefined && this.estimate.client != null) {
        this.sedDataClientInView(this.estimate.client.name, this.estimate.client.fullAdress);
      }
      this.sedDataInView();
      this.clinetExist = true;
      this.setTabletsAtributte(this.estimate);
    }
  }


  sedDataInView(): void {
    this.tileAndForm.patchValue({
      overhead: this.estimate.overhead,
      description: this.estimate.description,
      title: this.estimate.title,
      starDate: this.estimate.starDate,
    });

    this.inputForm.patchValue({
      totalCostWork: this.serviceEstimate.round(this.estimate.totalCostWork),
      totalCostWorkWithoutTaxes: this.serviceEstimate.round(this.estimate.totalCostWorkWithoutTaxes),
    });
  }


  setTablets(estimate: Estimate): void {
    this.subcontractorsExist = true;
    this.billExist = true;
    this.servicesExist = true;
    this.services = estimate.services;
    this.bills = estimate.bills;
    this.subcontractors = estimate.subcontractors;
  }


  setTabletsAtributte(estimate: Estimate): void {
    this.setTablets(estimate);
    if (estimate.services.length > 0) { this.disableManualTotal(); }
    if (estimate.bills.length > 0) { this.disableManualTotal(); }
    if (estimate.subcontractors !== undefined && estimate.subcontractors.length > 0) { this.disableManualTotal(); }
    this.total();
  }


  addEvent(type: string, event: MatDatepickerInputEvent<Date>): void {
    this.estimate.starDate = this.tileAndForm.value.starDate;
  }

  onTitle(): void {
    this.estimate.title = this.tileAndForm.value.title;
  }

  onDescription(): void {
    this.estimate.description = this.tileAndForm.value.description;
  }

  sedDataClientInView(name: string, address: string): void {
    this.nameCliente = ` ${name}`;
    this.addressCliente = ` ${address}`;
  }


  clearInput(): void {
    this.tileAndForm.get('title').setValue('');
  }


  changueStatus(status: string): void {
    this.estimate.status = status;
    if(this.estimate.status == this.statusList[3]){
      this.save();
    }
  }

  onAddNewClient(editar?: boolean): void {

    let clients = new Client();
    editar !== undefined && editar != null && editar ? clients = this.estimate.client : editar = false;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = '80%';
    dialogConfig.data = {
      atributo: clients,
      edit: editar,
    };

    const dialogRef = this.dialog.open(NewClienteComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(x => {
      if (x !== undefined) {
        this.estimate.client = x;
        const name = `${this.estimate.client.name} ${this.estimate.client.lastName}`;
        this.sedDataClientInView(name, this.estimate.client.fullAdress);
        this.clinetExist = true;
      }
    });
  }

  onDeleteClient(): void {
    this.estimate.client = null;
    this.estimate.client = new Client();
    this.clinetExist = false;
  }



  onAddNewSubcontractors(editar?: boolean, subcontractorRow?: any): void {
    let service = new Subcontractor();
    editar !== undefined && editar != null && editar ? service = subcontractorRow : editar = false;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '95%';
    dialogConfig.height = '85%';
    dialogConfig.data = {
      atributo: service,
      edit: editar,
    };
    const dialogRef = this.dialog.open(NewSubContractorsComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((sub: DialogoSubContractors) => {

      if (sub !== undefined) {
        this.subcontractorsExist = true;

        if (sub.newSubContractor !== undefined && sub.newSubContractor != null) {

          if (this.estimate.client !== undefined && this.estimate.client != null) {
            sub.newSubContractor.codeClient = this.estimate.client.codeClient;
          }

          this.estimate.subcontractors.push(sub.newSubContractor);
          this.subcontractors = this.estimate.subcontractors;
          this.subContractorsTablet.updatedatasourse(this.estimate.subcontractors);
          this.total();
        }
      }
    });
  }


  addSubcontractor(subcontractor: Subcontractor): void {
    this.estimate.subcontractors.push(subcontractor);
    this.total();
  }


  deleteSubcontractor(subcontractor: Subcontractor): void {
    const listaSubcontractor = this.estimate.subcontractors.filter((y: Subcontractor) => y !== subcontractor);
    this.estimate.subcontractors = listaSubcontractor;
    this.checkListForEnableManualTotalInput();
  }



  onAddNewService(editar?: boolean, serviceRow?: any): void {

    let service = new ServiceHandyManTally();
    editar !== undefined && editar != null && editar ? service = serviceRow : editar = false;

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = 'auto';
    dialogConfig.height = 'auto';

    dialogConfig.data = {
      atributo: service,
      edit: editar,
    };

    const dialogRef = this.dialog.open(NewServiceComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((x: DialogoService) => {

      if (x !== undefined) {
        this.servicesExist = true;
        if (x.newService !== undefined && x.newService != null) {
          this.estimate.services.push(x.newService);
          this.serviceTablet.updatedataSourse(this.estimate.services);
        }
      }
      this.total();
    });
  }


  addService(service: ServiceHandyManTally): void {
    
    this.estimate.services.push(service);
    this.total();
  }

  deleteService(service: ServiceHandyManTally): void {
    // tslint:disable-next-line: max-line-length
    const listaServicios = this.estimate.services.filter((x: ServiceHandyManTally) => x !== service);
    this.estimate.services = listaServicios;
    this.checkListForEnableManualTotalInput();
  }



  onAddNewBill(editar?: boolean, billRow?: any): void {

    let bill = new Bill();
    editar !== undefined && editar != null && editar ? bill = billRow : editar = false;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = '80%';

    dialogConfig.data = {
      atributo: bill,
      edit: editar,
    };

    const dialogRef = this.dialog.open(NewBillComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((x: DialogoBill) => {

      if (x !== undefined) {
        if (x.newBill !== undefined && x.newBill != null) {
          this.billExist = true;
          this.estimate.bills.push(x.newBill);
          this.bills = this.estimate.bills;
          this.billListTablet.updatedatasourse(this.bills);
          this.total();
        }
      }
    });
  }


  addbill(bill: Bill): void {
    this.estimate.bills.push(bill);
    this.total();
  }

  deleteBill(bills: Bill): void {
    // tslint:disable-next-line: max-line-length
    const listaBills = this.estimate.bills.filter((x: Bill) => x !== bills);
    this.estimate.bills = listaBills;
    this.checkListForEnableManualTotalInput();
  }


  total(): void {
    let billTotal = 0;
    let billTotalWichoutTaxes = 0;
    let billWichoutTaxes = 0;
    let servicesTotal = 0;
    let total = 0;
    let totalWichtTaxes = 0;
    let subcontractorsTotalCostOfwork = 0;

    this.servicesTotal = 0;
    this.subcontractorsTotal = 0;
    this.billLastTotal = 0;

    this.subcontractorsTotalProfit = 0;
    this.servicesTotalOverhead = 0;
    this.billTotalOverhead = 0;

    // Bill...
    if (this.estimate.bills != null && this.estimate.bills !== undefined && this.estimate.bills.length > 0) {
      this.estimate.bills.forEach(bill => {
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
    // this.billLastTotal = billTotal + this.billTotalOverhead;
    this.billLastTotal = billTotal;

    // services...
    if (this.estimate.services != null && this.estimate.services !== undefined && this.estimate.services.length > 0) {
      this.estimate.services.forEach(service => {
        if (!service.itemDeliteEdit) {
          servicesTotal += service.servicesCost;
        }

      });
      this.disableManualTotal();
      this.servicesTotalOverhead = this.calculateOverheads(servicesTotal);
      // this.servicesTotal = this.servicesTotalOverhead + servicesTotal;
      this.servicesTotal = servicesTotal;
    }


    // subcontractors...
    if (this.estimate.subcontractors != null && this.estimate.subcontractors !== undefined && this.estimate.subcontractors.length > 0) {
      let totalcostForProfitCalculation = 0;

      this.estimate.subcontractors.forEach(subcontractors => {
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
          this.subcontractorsTotalProfit += this.serviceEstimate.round(subcontractors.profitCalculation);
        }

      });
      this.disableManualTotal();
    }

    // tslint:disable-next-line: max-line-length
    total = this.servicesTotal + this.subcontractorsTotal + this.servicesTotalOverhead + billWichoutTaxes + this.billTotalWichoutTaxesOverhead;
    // tslint:disable-next-line: max-line-length
    totalWichtTaxes = this.servicesTotal + this.subcontractorsTotal + this.servicesTotalOverhead + this.billLastTotal + this.billTotalOverhead;


    const roumWichOutTaxes = this.serviceEstimate.round(total);
    const roumWithTaxes = this.serviceEstimate.round(totalWichtTaxes);

    this.inputForm.patchValue({
      totalCostWorkWithoutTaxes: roumWichOutTaxes,
      totalCostWork: roumWithTaxes,
    });

    this.estimate.totalCostWork = roumWithTaxes;
    this.estimate.totalCostWorkWithoutTaxes = roumWichOutTaxes;
  }

  calculateProfit(valor: number, profit: number): number {
    let resultado: number;
    if (profit !== undefined && profit != null) {
      resultado = valor * (profit / 100);
      return this.serviceEstimate.round(resultado);
    } else {
      return this.serviceEstimate.round(valor);
    }
  }

  calculateOverheads(valor: number): number {
    let resultado: number;
    if (this.tileAndForm.value.overhead !== undefined && this.tileAndForm.value.overhead != null) {
      resultado = (valor * (this.tileAndForm.value.overhead / 100));
      return this.serviceEstimate.round(resultado);
    } else {
      return 0;
    }
  }

  calculateOverhead(valor: number): number {
    let resultado: number;
    if (this.tileAndForm.value.overhead !== undefined && this.tileAndForm.value.overhead != null) {
      resultado = valor + (valor * (this.tileAndForm.value.overhead / 100));
      return resultado;
    } else {
      return 0;
    }
  }

  totalWithTaxesCalculation(valor: number, calcula: boolean): void {
    let valorWithTaxes = 0;
    if (calcula) {
      valorWithTaxes = valor + (valor * (this.seccion.taxes / 100));
    } else {
      valorWithTaxes = valor;
    }

    const roumWithTaxes = this.serviceEstimate.round(valorWithTaxes);
    this.inputForm.patchValue({
      totalCostWork: roumWithTaxes,
    });
    this.estimate.totalCostWork = roumWithTaxes;
  }


  totalWichOutTaxesCalculation(valor: number, calcula: boolean): void {
    let valorWichOutTaxes = 0;
    if (calcula) {
      valorWichOutTaxes = valor - (valor * (this.seccion.taxes / 100));
    } else {
      valorWichOutTaxes = valor;
    }

    const roumWichOutTaxes = this.serviceEstimate.round(valorWichOutTaxes);
    this.inputForm.patchValue({
      totalCostWorkWithoutTaxes: roumWichOutTaxes,
    });
    this.estimate.totalCostWorkWithoutTaxes = roumWichOutTaxes;
  }


  totalManualWichTaxes(): void {
    let totalValorWithOverhead = 0;

    if (this.estimate.overhead !== undefined && this.estimate.overhead != null) {
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
    if (this.estimate.overhead !== undefined && this.estimate.overhead != null) {
      totalValorWithOverhead = this.calculateOverhead(this.inputForm.value.totalCostWorkWithoutTaxes);
    } else {
      totalValorWithOverhead = this.inputForm.value.totalCostWorkWithoutTaxes;
    }

    this.totalWichOutTaxesCalculation(totalValorWithOverhead, false);
    this.totalWithTaxesCalculation(totalValorWithOverhead, true);
    this.totalMnual = totalValorWithOverhead;
  }

  onOverhead(): void {
    this.estimate.overhead = this.tileAndForm.value.overhead;
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
    if (this.estimate.bills.length == 0 && this.estimate.services.length == 0 && this.estimate.subcontractors.length == 0) {
      this.total();
      this.enableManualTotal();
    } else {
      this.total();
    }
  }



  saveNotification(): void {

    let mensaje = 'Confirm that you want to save the Estimate?';
    let titles = 'Save Alert';

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';

    // 'CANCELED', 'PAUSE'

    if (this.estimate.status === 'APPROVED') {
      let mensaje = '"An approved Estimate cannot be saved. You must change the status to be able to save."';
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
  }


  save(): void {
    // tslint:disable-next-line: max-line-length
    if (this.estimate.idEstimate !== undefined && this.estimate.idEstimate != null
         && this.estimate.codeWork !== undefined && this.estimate.codeWork !== null) {

      this.serviceEstimate.saveAndUpdateEstimate(this.estimate).subscribe(response => {
        this.estimate = response;
        this.viewEstimate();
      }, error => {
        console.log('Error', error);
        const notification = '"An error occurred while trying to Update Estimate"';
        this.openSnackBar(notification, 'Close');
      });

    } else {
      console.log('this.estimate::', JSON.stringify(this.estimate));
      this.serviceEstimate.saveEstimate(this.estimate).subscribe(response => {
        this.estimate = response;
        this.viewEstimate();
      }, error => {
        console.log('Error', error);
        const notification = '"An error occurred while trying to save a new Estimate"';
        this.openSnackBar(notification, 'Close');
      }
      );
    }
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

    this.estimate = null;
    this.router.navigateByUrl('/jshandy-man-services/menu/home');
  }


  onCopyEstimate(): void {
    if (this.estimate.idEstimate !== undefined && this.estimate.idEstimate != null) {
      this.notification('Client copy confirmation', 'You also want to copy the Client loaded in the Estimate', 'copy', 'No', 'Yes');
    } else {
      const notification = '"An Estimate cannot be copied without being Saved first"';
      this.openSnackBar(notification, 'Close');
    }

  }


  onConvertEstimate(): void {
    if (this.estimate.idEstimate !== undefined && this.estimate.idEstimate != null) {
      const mensg = 'You want to turn the Estimate into an effective job to generate an invoice';
      this.notification(' Convert Estimate to Job Confirmation', mensg, 'convert', 'Cancel', 'Agree');
    } else {
      const notification = '"An Estimate cannot be convert without being Saved first"';
      this.openSnackBar(notification, 'Close');
    }
  }

  onDeleteEstimate(): void {
    if (this.estimate.idEstimate !== undefined && this.estimate.idEstimate != null) {
      const mensg = 'If you want to delete the estimate, by accepting you will lose access to this information permanently.';
      this.notification('Delete Estimate Confirmation', mensg, 'delete', 'Cancel', 'Agree');
    } else {
      const notification = '"An Estimate cannot be delete without being Saved first"';
      this.openSnackBar(notification, 'Close');
    }
  }


  copyEstimate(response: boolean): void {
    let newEstimate = new Estimate();
    newEstimate = this.serviceEstimate.copyEstimate(this.estimate, response);
    this.estimate = newEstimate;
    this.estimate.status = AppSettings.NewESTIMATE;
    this.viewEstimate();
    this.vistaName = `New Estimate`;
    this.disabledTab = true;
    if (!response) {
      this.nameCliente = ``;
      this.addressCliente = ``;
      this.clinetExist = false;
    }
    if (newEstimate.starDate === undefined || newEstimate.starDate != null) {
      this.tileAndForm.patchValue({
        starDate: null,
      });
    }
  }

  notification(titles: string, mensaje: string, action: string, cancels: string, confirms: string, data?: any): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';
    dialogConfig.data = { title: titles, msg: mensaje, cancel: cancels, confirm: confirms };
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((response: boolean) => {
      if (response) {
        if (action === 'copy') { this.copyEstimate(response); }
        if (action === 'delete') { this.deleteEstimate(); }
        if (action === 'convert') { this.covertEstimate(this.estimate); }
        if (action === 'convertCheckInvoice') { this.onNavegateToInvoice(data); }
      }
    });
  }


  onNavegateToInvoice(rowId: number): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/invoice/edid-and-detail'],
      { queryParams: { id: rowId } }
    );
  }

  covertEstimate(body: Estimate): void {
    body.status = AppSettings.APPROVED;

    this.spinnerService.spinnerActive = false;
    this.spinnerService.show();

    this.serviceEstimate.checkInvoiceToEstimate(body.idEstimate).subscribe((response: EntityRespone) => {
      const work: Work = response.entidades[0];
      const notification = work.idWork !== undefined || work.idWork != null ? 'There is already an invoice created, its status is active or paused' : '" the invoice will be created"';
      if (work.idWork === undefined || work.idWork == null) {
        this.serviceEstimate.saveAndUpdateEstimate(body).subscribe(response => {
          this.covert(body);
        }, error => {
          // this.spinnerService.hide();
          console.log('Error', error);
          this.covert(body);
        });
      } else {
        this.spinnerService.hide();
        this.spinnerService.spinnerActive = true;
        // tslint:disable-next-line: max-line-length
        this.notification('Convert Estimate to Job Confirmation', notification, 'convertCheckInvoice', 'Cancel', 'Go to Invoice', work.idWork);
        // this.openSnackBar(notification, 'Close');
      }
    }, error => {
      console.log('Error', error);
      this.spinnerService.hide();
      this.spinnerService.spinnerActive = true;
    });
  }

  covert(body: Estimate): void {
    this.serviceEstimate.covertEstimate(body).subscribe(response => {
      this.spinnerService.hide();
      this.spinnerService.spinnerActive = true;
      const notification = response ? '"the work was create"' : '" the invoice could not be created"';
      this.openSnackBar(notification, 'Close');
      if (response) {
        this.router.navigateByUrl('/jshandy-man-services/menu/invoice/list');
      } else {
        return;
      }
    }, error => {
      this.spinnerService.hide();
      this.spinnerService.spinnerActive = true;
      console.log('Error', error);
      const notification = '"An error occurred while trying to check the id of Estimate"';
      this.openSnackBar(notification, 'Close');
    });
  }


  deleteEstimate(): void {
    this.spinnerService.spinnerActive = false;
    this.spinnerService.show();

    this.estimate.status = AppSettings.CANCELED;
    this.serviceEstimate.saveAndUpdateEstimate(this.estimate).subscribe(response => {
      this.delete();
    }, error => {
      console.log('Error', error);
      this.delete();
    });
  }

  delete(): void {
    this.serviceEstimate.deleteEsimate(this.estimate.idEstimate.toString()).subscribe(response => {
      const notification = response ? '"the Estimate was deleted"' : '"the Estimate cant not be delete"';
      this.openSnackBar(notification, 'Close');
      this.spinnerService.hide();
      this.spinnerService.spinnerActive = true;
      if (response) {
        this.router.navigateByUrl('/jshandy-man-services/menu/estimate/list');
      } else {
        return;
      }
    });
  }

  cambio(): void {
    this.buttonView = this.buttonView ? false : true;
  }


  mostrarTotalCost(): void {

    // const re = document.getElementById('reloj');
    // const fecDatos = document.getElementById('fec_Datos');

    // re.textContent = `Total Cost:`;
    // // fecDatos.textContent = `$500`;
  }


  showDivWithHoverStyles(divId: number): void {
    this.hoveredDivId = divId;
  }

  showAllDivsWithDefaultStyles(): void {
    this.hoveredDivId = 1;
  }




}



