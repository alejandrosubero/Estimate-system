import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { Taxes } from 'src/app/models/taxes.model';
import { ConfigurationService } from 'src/app/services/configuration.service';
import { CoreService } from 'src/app/services/core.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-taxes-configuration',
  templateUrl: './taxes-configuration.component.html',
  styleUrls: ['./taxes-configuration.component.css']
})
export class TaxesConfigurationComponent implements OnInit {

  profileForm = this.fb.group({
    taxes: null,
    overHead: null,
    description: null
  });

  seccion: LogingResponse;
  public notification: string;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private coreService: CoreService,
    private _snackBar: MatSnackBar,
    private configurationService: ConfigurationService,
    private sessionService: SessionStorageService) {
    this.seccion = this.sessionService.get('UserSession');
  }

  ngOnInit(): void {
  }


  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
  }

  send(): void {

    let taxes = new Taxes();

    let dataExist = false;

    if (this.profileForm.value.taxes != null) {
      taxes.taxes = this.profileForm.value.taxes;
      dataExist = true;
    }

    if (this.profileForm.value.overHead != null) {
      taxes.overHead = this.profileForm.value.overHead;
      dataExist = true;
    }

    if (this.profileForm.value.description != null) {
      taxes.description = this.profileForm.value.description;
      dataExist = true;
    }

    if (dataExist) {
      this.configurationService.sendTaxes(taxes).subscribe((response: boolean) => {
        if (response) {
          this.seccion.taxes = taxes.taxes;
          // sessionStorage.removeItem('UserSession');
          // sessionStorage.clear();
          // sessionStorage.setItem('UserSession', JSON.stringify( this.seccion));
          this.sessionService.set('UserSession', this.seccion);
          // this.sessionService.setItem('taxes', taxes.taxes);
          this.notification = response ? 'the taxes is save' : '"An error occurred while trying to save the taxes try again later"';
          this.openSnackBar(this.notification, 'Close');
          this.onSelltings();
        } else {
          this.notification = response ? 'the taxes is save' : '"An error occurred while trying to save the taxes try again later"';
          this.openSnackBar(this.notification, 'Close');
        }
      }, error => {
        console.log('Error', error);
        this.notification = '"An error occurred while trying to save the taxes"';
        this.openSnackBar(this.notification, 'Close');
      });
    }
  }


  clearInput(): void {
    this.profileForm.patchValue({
      taxes: '',
      overHead: '',
      description: ''
    });
  }


  sedDataInView(): void {
    this.profileForm.patchValue({
      taxes: this.seccion.taxes,
    });
  }

  onSelltings(): void {
    this.clearInput();
    this.coreService.navigateByUrl('/jshandy-man-services/menu/configuration/menu');
  }



}
