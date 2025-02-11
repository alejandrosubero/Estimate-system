import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NewUser } from 'src/app/models/newUser.model';
import { ResponseModel } from 'src/app/models/response.model';
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
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  profileForm = this.fb.group({
    userFirsName: ['', Validators.required],
    userLastName: ['', Validators.required],
    mail: ['', [Validators.required, emailValidator]],
    userName: ['', Validators.required],
    password: ['', Validators.required],
    keyAcces: ['', Validators.required],
    imagen: [''],
    pregunta: ['', Validators.required],
    respuesta: ['', Validators.required],
  });

  public icon = 'visibility';
  public iconUser = 'visibility';
  public notification: string;
  isNotification = false;
  haveKeyAcces = false;
  craftImagePreview: any;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private _snackBar: MatSnackBar,
    private userService: UserService) { }

  ngOnInit(): void {
    // this.craftImagePreview = '../../../assets/images/Logo_tally_handy_man-1.png';
    // this.craftImagePreview = '../../../assets/images/user-profile1.png';
    this.craftImagePreview = 'assets/images/user-profile1.png';
    this.clearInput();
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

  sendNewUser(): void {

    if (this.profileForm.valid) {
      const newUser = new NewUser();
      newUser.userFirsName = this.profileForm.value.userFirsName;
      newUser.userLastName = this.profileForm.value.userLastName;
      newUser.mail = this.profileForm.value.mail;
      newUser.userName = this.profileForm.value.userName.trim();
      newUser.password = this.profileForm.value.password.trim();
      newUser.pregunta = this.profileForm.value.pregunta;
      newUser.respuesta = this.profileForm.value.respuesta;
      newUser.imagen = this.craftImagePreview;
      const keyAdmin = this.profileForm.value.keyAcces;
      // console.log(JSON.stringify(newUser));
      const user = this.userService.encryptUser(newUser);

      this.userService.newUser(user, keyAdmin).subscribe((response: ResponseModel) => {

        if (response.error === 'newUserExist01') {
          this.notification = 'Username already exist use another username';
          this.openSnackBar(this.notification, 'Close');
          return;
        }

        if (response.error === 'newUserExist02') {
          this.notification = 'the Mail already exist use another Mail';
          this.openSnackBar(this.notification, 'Close');
          return;
        }

        if (response.error!='newUserExist02' && response.error != 'newUserExist01') {
          this.onCancel();
        }

        // if (response.error == '') {
        //   this.onCancel();
        // }

      }, error => {
        console.log('Error', error);
        this.notification = '"An error occurred while trying to save a new user"';
        this.openSnackBar(this.notification, 'Close');
      });
    }

  }

  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
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
    this.router.navigateByUrl('/login');
  }

  onHaveKeyAcces(): void {
    this.haveKeyAcces = this.haveKeyAcces ? false : true;
  }


  onFileChange2(event): void {
    if (event.target.files && event.target.files.length > 0) {
      const reader = new FileReader();
      reader.onload = (event: any) => {
        this.craftImagePreview = event.target.result;
        // console.log(event.target.result);
      };
      const file = event.target.files[0];
      reader.readAsDataURL(file);
    }
  }




}
