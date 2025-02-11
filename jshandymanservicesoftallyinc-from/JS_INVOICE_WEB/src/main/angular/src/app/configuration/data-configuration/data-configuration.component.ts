import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AppSettings } from 'src/app/models/app-const.model';
import { DataJshandyManServices } from 'src/app/models/DataJshandyManServices.model';
import { EmailHandyManTally } from 'src/app/models/email-handy-man-tally.model';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { Work } from 'src/app/models/work.model';
import { ConfigurationService } from 'src/app/services/configuration.service';
import { CoreService } from 'src/app/services/core.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

// tslint:disable-next-line: typedef
export function emailValidator(control: AbstractControl) {
  var EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
  if (!EMAIL_REGEXP.test(control.value)) {
    return { invalidEmail: true };
  }
  return null;
}


@Component({
  selector: 'app-data-configuration',
  templateUrl: './data-configuration.component.html',
  styleUrls: ['./data-configuration.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DataConfigurationComponent implements OnInit {


  profileForm = this.fb.group({
    direction: ['', Validators.required],
    mail: ['', [Validators.required, emailValidator]],
    web: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    taxRegNumber: ['', Validators.required],
    company: ['', Validators.required],
    deadLineAlert: ['', Validators.required],
    coments1: null,
    coments2: null,
    coments3: null,
    coments4: null,
  });

  work: Work = JSON.parse(AppSettings.work2);
  email: EmailHandyManTally = new EmailHandyManTally();
  seccion: LogingResponse = new LogingResponse();
  subcontractorsTotalProfit = 0;
  billTotalOverhead: number = 0;
  servicesTotalOverhead: number = 0;
  servicesTotal = 0;
  billLastTotal = 0;
  billTotalWichoutTaxesOverhead = 0;
  subcontractorsTotal = 0;
  public notification: string;
  public dataJshandyManServices: DataJshandyManServices = new DataJshandyManServices();


  constructor(
    private router: Router,
    private coreService: CoreService,
    private fb: FormBuilder,
    private _snackBar: MatSnackBar,
    private configurationService: ConfigurationService,
    private sessionService: SessionStorageService) {
    this.seccion = this.sessionService.get('UserSession');
  }

  ngOnInit(): void {
    this.CheckDataSeccion();
  }

  CheckDataSeccion(): void {
    this.seccion.data != null && this.seccion.data !== undefined ? this.updateInput(this.seccion.data) : this.clearInput();
  }

  onSelltings(): void {
    this.clearInput();
    this.coreService.navigateByUrl('/jshandy-man-services/menu/configuration/menu');
  }

  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
  }

  clearInput(): void {
    this.profileForm.patchValue({
      direction: '',
      mail: '',
      web: '',
      phoneNumber: '',
      taxRegNumber: '',
      company: '',
      coments1: '',
      coments2: '',
      coments3: '',
      coments4: '',
      deadLineAlert: '',
    });
    this.dataJshandyManServices.userCode = this.seccion.userCode;
    this.dataJshandyManServices.active = true;
  }

  updateInput(dataJshandyMan: DataJshandyManServices): void {

    this.profileForm.patchValue({
      direction: dataJshandyMan.direction,
      mail: dataJshandyMan.mail,
      web: dataJshandyMan.mail,
      phoneNumber: dataJshandyMan.phoneNumber,
      taxRegNumber: dataJshandyMan.taxRegNumber,
      company: dataJshandyMan.companyName,
      coments1: dataJshandyMan.coments1,
      coments2: dataJshandyMan.coments2,
      coments3: dataJshandyMan.coments3,
      coments4: dataJshandyMan.coments4,
      deadLineAlert: dataJshandyMan.deadLineAlert,
    });

    this.dataJshandyManServices = dataJshandyMan;
    this.dataJshandyManServices.userCode = this.seccion.userCode;
    this.dataJshandyManServices.active = true;
    this.total();
  }


  updateInputDirection(): void {
    this.dataJshandyManServices.direction = this.profileForm.value.direction;
  }

  updateInputMail(): void {
    this.dataJshandyManServices.mail = this.profileForm.value.mail;
  }

  updateInputWeb(): void {
    this.dataJshandyManServices.web = this.profileForm.value.web;
  }

  updateInputPhoneNumber(): void {
    this.dataJshandyManServices.phoneNumber = this.profileForm.value.phoneNumber;
  }

  updateInputTaxRegNumber(): void {
    this.dataJshandyManServices.taxRegNumber = this.profileForm.value.taxRegNumber;
  }

  updateInputCompany(): void {
    this.dataJshandyManServices.companyName = this.profileForm.value.company;
  }

  updateInputComents1(): void {
    this.dataJshandyManServices.coments1 = this.profileForm.value.coments1;
  }

  updateInputComents2(): void {
    this.dataJshandyManServices.coments2 = this.profileForm.value.coments2;
  }

  updateInputComents3(): void {
    this.dataJshandyManServices.coments3 = this.profileForm.value.coments3;
  }

  updateInputComents4(): void {
    this.dataJshandyManServices.coments4 = this.profileForm.value.coments4;
  }

  updateInputDeadLineAlert(): void {
    this.dataJshandyManServices.deadLineAlert = this.profileForm.value.deadLineAlert;
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


    // Bill...
    if (this.work.bills != null && this.work.bills !== undefined && this.work.bills.length > 0) {
      this.work.bills.forEach(bill => {
        if (!bill.itemDeliteEdit) {
          billTotal += bill.billTotal;
          billTotalWichoutTaxes += bill.billTotalWichoutTaxes;
        }

      });
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

          if (subcontractors.billListSubcontractor !== undefined
            && subcontractors.billListSubcontractor != null
            && subcontractors.billListSubcontractor.length > 0) {

            subcontractors.billListSubcontractor.forEach(billsub => {
              totalcostForProfitCalculation += billsub.billTotal;
            });
          }
          subcontractors.profitCalculation = (subcontractors.costOfwork + totalcostForProfitCalculation) * (subcontractors.profit / 100);
          this.subcontractorsTotalProfit += this.coreService.round(subcontractors.profitCalculation);
        }
      });
    }

    // tslint:disable-next-line: max-line-length
    total = this.servicesTotal + billWichoutTaxes + this.subcontractorsTotal + this.servicesTotalOverhead + this.billTotalWichoutTaxesOverhead;

    // tslint:disable-next-line: max-line-length
    totalWichtTaxes = this.servicesTotal + this.subcontractorsTotal + this.billLastTotal + this.servicesTotalOverhead + this.billTotalOverhead;

    const roumWichOutTaxes = this.coreService.round(total);
    const roumWithTaxes = this.coreService.round(totalWichtTaxes);

    this.work.totalCostWork = roumWithTaxes;
    this.work.totalCostWorkWithoutTaxes = roumWichOutTaxes;

  }


  calculateOverheads(valor: number): number {
    let resultado: number;
    if (this.work.overhead !== undefined && this.work.overhead != null) {
      resultado = (valor * (this.work.overhead / 100));
      return this.coreService.round(resultado);
    } else {
      return this.coreService.round(valor);
    }
  }


  send(): void {

    this.configurationService.sendConfigurationData(this.dataJshandyManServices).subscribe((response: DataJshandyManServices) => {

      this.notification = response.idDataConfig != null ? 'the template configuration was saved' : '"An error occurred while trying to save the template configuration try again later"';

      if (response.idDataConfig != null) {
        this.seccion.data = response;
        this.sessionService.set('UserSession', this.seccion);
        this.onSelltings();
      }

      this.openSnackBar(this.notification, 'Close');
    }, error => {
      console.log('Error', error);
      this.notification = '"An error occurred while trying to save the template configuration try again late"';
      this.openSnackBar(this.notification, 'Close');
    });
  }



}
