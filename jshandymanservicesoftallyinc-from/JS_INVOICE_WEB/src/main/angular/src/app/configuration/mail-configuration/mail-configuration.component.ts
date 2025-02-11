import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AppSettings } from 'src/app/models/app-const.model';
import { EmailDataConfig } from 'src/app/models/email-data-config.model';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { Parameters } from 'src/app/models/parameter.model';
import { ConfigurationService } from 'src/app/services/configuration.service';
import { CoreService } from 'src/app/services/core.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { SpinnerOverlayService } from 'src/app/services/spinner-overlay.service';


// tslint:disable-next-line: typedef
export function emailValidator(control: AbstractControl) {
  var EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
  if (!EMAIL_REGEXP.test(control.value)) {
    return { invalidEmail: true };
  }
  return null;
}

@Component({
  selector: 'app-mail-configuration',
  templateUrl: './mail-configuration.component.html',
  styleUrls: ['./mail-configuration.component.css']
})
export class MailConfigurationComponent implements OnInit {

  profileForm = this.fb.group({
    host: ['', Validators.required],
    port: ['', Validators.required],
    mailusername: ['', [Validators.required, emailValidator]],
    mailpassword: ['', Validators.required],
  });


  seccion: LogingResponse;
  public notification: string;
  emailDataConfig: EmailDataConfig;
  public icon = 'visibility';
  public iconUser = 'visibility';

  constructor(
    private router: Router,
    private coreService: CoreService,
    private fb: FormBuilder,
    private _snackBar: MatSnackBar,
    private configurationService: ConfigurationService,
    private sessionService: SessionStorageService,
    private spinnerService: SpinnerOverlayService,) {
    this.seccion = this.sessionService.get('UserSession');
  }


  ngOnInit(): void {
    this.emailDataConfig = new EmailDataConfig();
    this.sedDataInView();
  }

  onSelltings(): void {
    this.clearInput();
    this.emailDataConfig = new EmailDataConfig();
    this.coreService.navigateByUrl('/jshandy-man-services/menu/configuration/menu');
  }


  sedDataInView(): void {
    this.profileForm.patchValue({
      host: this.emailDataConfig.host,
      port: this.emailDataConfig.port,
    });
  }


  viewUserName(): void {
    const userNameInput = document.getElementById('mailusername');
    if (userNameInput.getAttribute('type') == 'password') {
      userNameInput.setAttribute('type', 'text');
      this.iconUser = 'visibility_off';
    } else {
      userNameInput.setAttribute('type', 'password');
      this.iconUser = 'visibility';
    }
  }


  viewPassword(): void {
    const passwordInput = document.getElementById('mailpassword');
    if (passwordInput.getAttribute('type') == 'password') {
      passwordInput.setAttribute('type', 'text');
      this.icon = 'visibility_off';
    } else {
      passwordInput.setAttribute('type', 'password');
      this.icon = 'visibility';
    }
  }


  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
  }

  clearInput(): void {
    this.profileForm.patchValue({
      host: '',
      port: '',
      mailusername: '',
      mailpassword: '',
    });
  }

  send(): void {

    if (this.profileForm.valid) {

      let encryptMailData = new EmailDataConfig();

      if (this.profileForm.value.host != null) {
        this.emailDataConfig.host = this.profileForm.value.host;
      }

      if (this.profileForm.value.port != null) {
        this.emailDataConfig.port = this.profileForm.value.port;
      }

      if (this.profileForm.value.mailusername != null) {
        this.emailDataConfig.mailusername = this.profileForm.value.mailusername;
      }

      if (this.profileForm.value.mailpassword != null) {
        this.emailDataConfig.mailpassword = this.profileForm.value.mailpassword;
      }

      encryptMailData = this.encryptMail(this.emailDataConfig);

      this.configurationService.sendEmailconfig(encryptMailData).subscribe((response: boolean) => {
        this.notification = response ? 'the configuration is save' : '"An error occurred while trying to save the mail try again later"';
        this.openSnackBar(this.notification, 'Close');

        if (response) {
          this.spinnerService.hide();
          let parameter = new Parameters();
          parameter.nota = 'from automatic generation';
          parameter.clave = AppSettings.CLAVE;
          parameter.description = AppSettings.PARAMETERDESCRIPT;
          parameter.valor = this.emailDataConfig.port;
          this.configurationService.saveParameter(parameter).subscribe((x: boolean) => {
            if (x) {
              this.onSelltings();
            }
          }, error => {
            console.log('Error', error);
            this.notification = '"An error occurred while trying to save the parameter configuration"';
            this.openSnackBar(this.notification, 'Close');
          });
        }

      }, error => {
        console.log('Error', error);
        this.notification = '"An error occurred while trying to save the configuration"';
        this.openSnackBar(this.notification, 'Close');
      });

    } else {
      this.notification = '"Fill in all the fields Correctly"';
      this.openSnackBar(this.notification, 'Close');
    }

  }


  encryptMail(emailDataConfig: EmailDataConfig): EmailDataConfig {
    let encrypt = new EmailDataConfig();
    encrypt.port = this.configurationService.encrypt(emailDataConfig.port);
    encrypt.host = this.configurationService.encrypt(emailDataConfig.host);
    encrypt.mailusername = this.configurationService.encrypt(emailDataConfig.mailusername);
    encrypt.mailpassword = this.configurationService.encrypt(emailDataConfig.mailpassword);
    return encrypt;
  }



}
