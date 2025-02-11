import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { EntityRespone } from 'src/app/models/entity-respone.model';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { UserRecovery } from 'src/app/models/UserRecovery.model';
import { CoreService } from 'src/app/services/core.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { UserService } from 'src/app/services/user.service';


// tslint:disable-next-line: typedef
export function emailValidator(control: AbstractControl) {
  var EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
  if (!EMAIL_REGEXP.test(control.value)) {
    return { invalidEmail: true };
  }
  return null;
}

@Component({
  selector: 'app-recovery-user-data',
  templateUrl: './recovery-user-data.component.html',
  styleUrls: ['./recovery-user-data.component.css']
})
export class RecoveryUserDataComponent implements OnInit {

  userRecovery: UserRecovery = new UserRecovery();
  loguin = new LogingResponse();
  public notification: string;
  isNotification = false;
  infoText = 'To recover the password or the user, put the mail:';
  questionRecive = false;
  title = 'Recovery user Data';

  mail: string;
  pregunta: string;
  respuesta: string;
  private present$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private coreService: CoreService,
    private _snackBar: MatSnackBar,
    private userService: UserService,
    private sessionService: SessionStorageService) { }

  ngOnInit(): void {
    this.coreService.userRecovery$.pipe(takeUntil(this.present$)).subscribe(recovery => {
      this.setInput(recovery);
      if (recovery.pregunta !== '') {
        this.questionRecive = true;
      }
    });
  }


  setInput(recovery: UserRecovery): void {
    this.mail = recovery.mail;
    this.pregunta = recovery.pregunta;
    this.respuesta = recovery.respuesta;
  }


  onSend(): void {
    this.userRecovery.mail = this.mail;
    this.coreService.loginRecoveryData(this.userRecovery).subscribe((response: EntityRespone) => {
      if (response.entidades.length > 0) {
        let recoveryResponse: UserRecovery = new UserRecovery();
        recoveryResponse = response.entidades[0];
        recoveryResponse.respuesta = '';
        this.questionRecive = true;
        this.infoText = 'To recover the password or the user enter the correct answer to the question: ';
        this.coreService.updateduserRecovery(recoveryResponse);
        //  this.setInput(recoveryResponse);
        console.log('recoveryResponse', recoveryResponse);
        this.coreService.navigateByUrl('/recoveryUser');
      }
    });
  }


  onRecovery(): void {

    this.userRecovery.mail = this.mail;
    this.userRecovery.respuesta = this.userService.encrypt(this.respuesta);
    this.coreService.loginRecovery(this.userRecovery).subscribe((response: EntityRespone) => {
      if (response.entidades.length > 0) {
        this.loguin = response.entidades[0];
        this.sessionService.set('UserSession', this.loguin);
        this.coreService.setloginAuht(this.loguin);
        this.coreService.addData();
        this.clearInput();
        this.coreService.navigateByUrl('/jshandy-man-services/menu');
        this.coreService.updateduserRecovery(new UserRecovery());
      } else {
        if (response.error != null && response.error !== undefined && response.error.includes('403')) {
          this.clearInput();
          this.isNotification = true;
          this.notification = '** The Answer is incorrect **';
        } else {
          this.clearInput();
          this.isNotification = true;
          this.notification = ' --> The data entered is not corret enter valid data <--';
        }
      }

    }, error => {
      this.clearInput();
      this.isNotification = true;
      this.notification = ' --> The data entered is not corret enter valid data <--';
    });

  }

  onCancel(): void {
    this.userRecovery = new UserRecovery();
    this.coreService.navigateByUrl('/login');
  }

  clearInput(): void {
    this.mail = '';
    this.pregunta = '';
    this.respuesta = '';
  }


}
