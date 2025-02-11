import { Component, OnInit, ViewChild } from '@angular/core';
import { MatAccordion } from '@angular/material/expansion';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AppSettings } from 'src/app/models/app-const.model';
import { ConfigurationService } from 'src/app/services/configuration.service';
import { CoreService } from 'src/app/services/core.service';
import { SpinnerOverlayService } from 'src/app/services/spinner-overlay.service';

@Component({
  selector: 'app-configuration-menu',
  templateUrl: './configuration-menu.component.html',
  styleUrls: ['./configuration-menu.component.css']
})
export class ConfigurationMenuComponent implements OnInit {

  @ViewChild(MatAccordion) accordion: MatAccordion;

  panelOpenState = false;
  public notification: string;

  constructor(
    private _snackBar: MatSnackBar,
    private configurationService: ConfigurationService,
    private router: Router,
    private spinnerService: SpinnerOverlayService,
    private coreService: CoreService) { }

  ngOnInit(): void {
  }

  onSelltingsUser(): void {
    this.coreService.navigateByUrl('/jshandy-man-services/menu/configuration/user');
  }

  onSelltingsData(): void {
    this.coreService.navigateByUrl('/jshandy-man-services/menu/configuration/data');
  }

  onSelltingsTaxes(): void {
    this.coreService.navigateByUrl('/jshandy-man-services/menu/configuration/taxes');
  }

  onSelltingsMail(): void {
    this.coreService.navigateByUrl('/jshandy-man-services/menu/configuration/mail');
  }

  onBackMenu(): void {
    this.coreService.navigateByUrl('/jshandy-man-services/menu/home');
  }

  onSetTemplate(): void { //imgTemplate
    this.show();
    this.configurationService.setAndSaveTemplate(AppSettings.temp1).subscribe((response: boolean) =>{
      this.notification = response ? 'the configuration is save' : '"An error occurred while trying set the templates try again later"';
      if(response){
        this.configurationService.setAndSaveTemplate(AppSettings.tempPaimentCar).subscribe((responseInterna: boolean) => {
          this.notification = responseInterna ? 'the configuration is save' : '"An error occurred while trying set the templates try again later"';
          if(responseInterna){
            this.configurationService.setAndSaveTemplate(AppSettings.imgTemplate).subscribe((responseInternaIng: boolean) => {
              this.notification = responseInterna ? 'the configuration is save' : '"An error occurred while trying set the templates try again later"';
              this.openSnackBar(this.notification, 'Close');
              this.hide();
            }, error => {
              console.log('Error', error);
              this.notification = '"An error occurred while trying to save the configuration template car"';
              this.hide();
              this.openSnackBar(this.notification, 'Close');
            });
          }else{
            this.openSnackBar(this.notification, 'Close');
            this.hide();
          }
        }, error => {
          console.log('Error', error);
          this.notification = '"An error occurred while trying to save the configuration template car"';
          this.hide();
          this.openSnackBar(this.notification, 'Close');
        });

      }else{
        this.openSnackBar(this.notification, 'Close');
        this.hide();
      }
    }, error => {
      console.log('Error', error);
      this.notification = '"An error occurred while trying to save the configuration template 1"';
      this.openSnackBar(this.notification, 'Close');
      this.hide();
    });

    // this.configurationService.setTemplate().subscribe((response: boolean) => {
    //   this.notification = response ? 'the configuration is save' : '"An error occurred while trying to save the taxes try again later"';
    //   this.openSnackBar(this.notification, 'Close');
    // }, error => {
    //   console.log('Error', error);
    //   this.notification = '"An error occurred while trying to save the configuration"';
    //   this.openSnackBar(this.notification, 'Close');
    // });
  }

  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
  }


  show(): void {
    this.spinnerService.spinnerActive = false;
    this.spinnerService.show();
  }

  hide(): void {
    this.spinnerService.hide();
    this.spinnerService.spinnerActive = true;
  }


}
