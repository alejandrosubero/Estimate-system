import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NewUser } from '../models/newUser.model';
import { AESEncryptDecryptService } from './aesencrypt-decrypt-service.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(protected http: HttpClient, private encryptService: AESEncryptDecryptService) { }


  // http://localhost:8090/jshandyman/new/user/saveNewUser
  newUser(body: any, clave: string): Observable<any> {
    const opciones = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        keyAdmin: clave
      }),
    };
    return this.http.post(environment.serverUrl + 'new/user/saveNewUser', body, opciones);
  }


  // http://localhost:8090/jshandyman/new/user/u
  updateUser(body: any): Observable<any> {
    const opciones = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };
    return this.http.post(environment.serverUrl + 'api/user/u', body, opciones);
  }


  getallUsers(): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `api/user/GetAllUser`, opciones);
  }


  encrypt(data): string {
    return this.encryptService.encripted(data);
  }


  decrypt(dataEcrypted): string {
    return this.encryptService.decrypted(dataEcrypted);
  }


  encryptUser(user: NewUser): NewUser {
    if (user !== undefined && user != null) {
      if (user.userFirsName !== undefined && user.userFirsName != null) {
        const data = this.encrypt(user.userFirsName);
        user.userFirsName = data;
      }
      if (user.userLastName !== undefined && user.userLastName != null) {
        const data = this.encrypt(user.userLastName);
        user.userLastName = data;
      }
      if (user.userName !== undefined && user.userName != null) {
        const data = this.encrypt(user.userName);
        user.userName = data;
      }
      if (user.password !== undefined && user.password != null) {
        const data = this.encrypt(user.password);
        user.password = data;
      }
      if (user.mail !== undefined && user.mail != null) {
        const data = this.encrypt(user.mail);
        user.mail = data;
      }

      if (user.respuesta !== undefined && user.respuesta != null) {
        const data = this.encrypt(user.respuesta);
        user.respuesta = data;
      }

      if (user.pregunta !== undefined && user.pregunta != null) {
        const data = this.encrypt(user.pregunta);
        user.pregunta = data;
      }

      if (user.imagen !== undefined && user.imagen != null) {
        const data = this.encrypt(user.imagen);
        user.imagen = data;
      }

    }
    return user;
  }



}
