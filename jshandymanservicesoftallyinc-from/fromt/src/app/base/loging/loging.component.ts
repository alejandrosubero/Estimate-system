import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EntityRespone } from 'src/app/models/entity-respone.model';
import { Loging } from 'src/app/models/login.model';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { CoreService } from 'src/app/services/core.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-loging',
  templateUrl: './loging.component.html',
  styleUrls: ['./loging.component.css']
})
export class LogingComponent implements OnInit {

  profileForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });


  public icon = 'visibility';
  public iconUser = 'visibility';
  public notification: string;
  isNotification = false;
  loguin = new LogingResponse();
  recoveryUser = 'If you forget your password or your user, click to recover your data.';
  constructor(
    private fb: FormBuilder,
    private coreService: CoreService,
    private router: Router,
    private sessionService: SessionStorageService) { }

  ngOnInit(): void { }


  viewUserName(): void {
    const userNameInput = document.getElementById('username');
    if (userNameInput.getAttribute('type') == 'password') {
      userNameInput.setAttribute('type', 'text');
      this.iconUser = 'visibility_off';
    } else {
      userNameInput.setAttribute('type', 'password');
      this.iconUser = 'visibility';
    }

  }


  viewPassword(): void {
    const passwordInput = document.getElementById('password');
    if (passwordInput.getAttribute('type') == 'password') {
      passwordInput.setAttribute('type', 'text');
      this.icon = 'visibility_off';
    } else {
      passwordInput.setAttribute('type', 'password');
      this.icon = 'visibility';
    }
  }


  onLogin(): void {
    this.sessionService.clear();
    const bodyLogin = new Loging();
    bodyLogin.username = this.profileForm.value.username.trim();
    bodyLogin.password = this.profileForm.value.password.trim();

    this.coreService.login(bodyLogin).subscribe((response: EntityRespone) => {
      if (response.entidades.length > 0) {
        this.loguin = response.entidades[0];
        this.sessionService.set('UserSession', this.loguin);
        this.coreService.setloginAuht(this.loguin);
        this.coreService.addData();
        this.clearInput();
        this.router.navigateByUrl('/jshandy-man-services/menu');
      } else {
        if (response.error != null && response.error !== undefined && response.error.includes('403')) {
          this.clearInput();
          this.isNotification = true;
          this.notification = '** The password or username is incorrect **';
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

  clearInput(): void {
    this.profileForm.get('username').setValue('');
    this.profileForm.get('password').setValue('');
  }

  onKey(): void {
    this.isNotification = false;
  }

  recovery(): void{
    this.clearInput();
    this.router.navigateByUrl('/recoveryUser');
  }

}
