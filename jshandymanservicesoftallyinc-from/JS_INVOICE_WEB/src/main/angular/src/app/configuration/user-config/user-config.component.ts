import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { NewUser } from 'src/app/models/newUser.model';
import { ResponseModel } from 'src/app/models/response.model';
import { CoreService } from 'src/app/services/core.service';
import { SanitizerPipeService } from 'src/app/services/sanitizer-pipe.service';
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
  selector: 'app-user-config',
  templateUrl: './user-config.component.html',
  styleUrls: ['./user-config.component.css']
})
export class UserConfigComponent implements OnInit {


  profileForm = this.fb.group({
    userFirsName: null,
    userLastName: null,
    mail: null,
    userName: null,
    password: null,
    keyAcces: null,
    imagen: null,
    pregunta: null,
    respuesta: null,
  });

  public icon = 'visibility';
  public iconUser = 'visibility';
  public notification: string;
  isNotification = false;
  haveKeyAcces = false;
  craftImagePreview: any;
  craftImagePreviewToSend: any;
  seccion: LogingResponse;

  constructor(
    private fb: FormBuilder,
    private coreService: CoreService,
    private _snackBar: MatSnackBar,
    private userService: UserService,
    private sanitiPipe: SanitizerPipeService,
    private sessionService: SessionStorageService) {
    this.seccion = this.sessionService.get('UserSession');
  }

  ngOnInit(): void {

    if (this.seccion !== undefined && this.seccion != null) {
      this.craftImagePreview = this.sanitiPipe.transform(this.seccion.userImagen);
      // console.log(this.seccion);
    } else {
      this.craftImagePreview = '../../../assets/images/user-profile1.png';
    }
  }


  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
  }

  clearInput(): void {
    this.profileForm.patchValue({
      userFirsName: '',
      userLastName: '',
      mail: '',
      userName: '',
      password: '',
      keyAcces: '',
      imagen: '',
      pregunta: '',
      respuesta: '',
    });

  }



  viewUserName(): void {
    const userNameInput = document.getElementById('userName');
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


  viewKeyAcces(): void {
    const passwordInput = document.getElementById('keyAcces');
    if (passwordInput.getAttribute('type') == 'password') {
      passwordInput.setAttribute('type', 'text');
      this.icon = 'visibility_off';
    } else {
      passwordInput.setAttribute('type', 'password');
      this.icon = 'visibility';
    }
  }

  onCancel(): void {
    this.clearInput();
    this.coreService.navigateByUrl('/jshandy-man-services/menu/configuration/menu');
  }

  onHaveKeyAcces(): void {
    this.haveKeyAcces = this.haveKeyAcces ? false : true;
  }


  onFileChange2(event): void {
    if (event.target.files && event.target.files.length > 0) {
      const reader = new FileReader();
      reader.onload = (event: any) => {
        this.craftImagePreview = event.target.result;
        this.craftImagePreviewToSend = event.target.result;
        // console.log(event.target.result);
      };
      const file = event.target.files[0];
      reader.readAsDataURL(file);
    }
  }


  onUpdateUser(): void {
    const newUser = new NewUser();

    if (this.profileForm.value.userFirsName != null) {
      newUser.userFirsName = this.profileForm.value.userFirsName;
    }

    if (this.profileForm.value.userLastName != null) {
      newUser.userLastName = this.profileForm.value.userLastName;
    }
    if (this.profileForm.value.mail != null) {
      newUser.mail = this.profileForm.value.mail;
    }
    if (this.profileForm.value.userName != null) {
      newUser.userName = this.profileForm.value.userName.trim();
    }
    if (this.profileForm.value.password != null) {
      newUser.password = this.profileForm.value.password.trim();
    }

    if (this.profileForm.value.pregunta != null) {
      newUser.password = this.profileForm.value.pregunta;
    }
    if (this.profileForm.value.respuesta != null) {
      newUser.password = this.profileForm.value.respuesta;
    }

    if (this.craftImagePreviewToSend !== this.seccion.userImagen) {
      newUser.imagen = this.craftImagePreviewToSend;
    }

    newUser.userCode = this.seccion.userCode;
    const user = this.userService.encryptUser(newUser);

    this.userService.updateUser(user).subscribe((response: ResponseModel) => {
      // console.log(response);
      if (response) {
        this.coreService.logout();
      }

      if (response.error == '') {
        this.coreService.logout();
      }

      if (response.error == 'newUserExist01') {
        this.notification = 'Username already exist use another username';
        this.openSnackBar(this.notification, 'Close');
      }

      if (response.error == 'newUserExist00') {
        this.notification = response.mensaje;
        this.openSnackBar(this.notification, 'Close');
      }

    }, error => {
      console.log('Error', error);
      this.notification = '"An error occurred while trying to save a user"';
      this.openSnackBar(this.notification, 'Close');
    } );
  }


}
