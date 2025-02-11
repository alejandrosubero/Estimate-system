import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DataJshandyManServices } from '../models/DataJshandyManServices.model';
import { LogingResponse } from '../models/loging-response.model';
import { UserRecovery } from '../models/UserRecovery.model';
import { SessionStorageService } from './session-storage.service';



@Injectable({
  providedIn: 'root'
})
export class CoreService {

  public userRecovery$ = new BehaviorSubject<UserRecovery>(new UserRecovery());
  evento = this.userRecovery$.asObservable();


  // tslint:disable-next-line: variable-name
  private _dataJshandyManServices: DataJshandyManServices = new DataJshandyManServices();

  // tslint:disable-next-line: variable-name
  private _loguin = new LogingResponse();

  constructor(protected http: HttpClient, private router: Router, private SessionService: SessionStorageService) { }


  updateduserRecovery(data: UserRecovery): void { this.userRecovery$.next(data); }

  setloginAuht(variable: LogingResponse): void {
    this._loguin = variable;
  }

  // http://localhost:8090/jshandyman/AuthoritiesCotroller/loginAuth
  login(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(environment.serverUrl + 'AuthoritiesCotroller/loginAuth', body, { headers });
  }



  // http://localhost:8090/jshandyman/AuthoritiesCotroller/loginAuth/recovery/user/data
  loginRecoveryData(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(environment.serverUrl + 'AuthoritiesCotroller/loginAuth/recovery/user/data', body, { headers });
  }


  // http://localhost:8090/jshandyman/AuthoritiesCotroller/loginAuth/recovery
  loginRecovery(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(environment.serverUrl + 'AuthoritiesCotroller/loginAuth/recovery', body, { headers });
  }


  // http://localhost:8090/jshandyman/api/email/send/Work
  sendWork(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(environment.serverUrl + 'api/email/send/Work', body, { headers });
  }

  // http://localhost:8090/jshandyman/new/user/start
  startClient(): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `new/user/start`, opciones);
  }


  search(keyword: string): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `api/search/keyword/${keyword}`, opciones);
  }


  // http://localhost:8090/jshandyman/api/search/keyword/between
  searchBetween(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(environment.serverUrl + 'api/search/keyword/between', body, { headers });
  }

  // http://localhost:8090/jshandyman/api/search/keyword/month/year
  searchMonthAndYear(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(environment.serverUrl + 'api/search/keyword/month/year', body, { headers });
  }



  logout(): void {
    this.SessionService.cleanAndRemove('UserSession');
    this.router.navigateByUrl('/login');
    // TODO: LE FALTA LA LLAMADA AL SERVICIO DE logout DEL BACK.
  }


  navigateByUrl(ruta: string): void {
    this.router.navigateByUrl(ruta);
  }


  round(num): number {
    const numero = Number((Math.abs(num) * 100).toPrecision(15));
    return Math.round(numero) / 100 * Math.sign(num);
  }


  // tslint:disable-next-line: typedef
  get loguin() { return this._loguin; }

  // tslint:disable-next-line: typedef
  get dataJshandy() {
    this.addData();
    return this._dataJshandyManServices;
  }


  addData(): void {
    let data: DataJshandyManServices = new DataJshandyManServices();
    data = this.SessionService.getItem('data');
    const configurationRedy = this.SessionService.getItem('configurationRedy');
    this._dataJshandyManServices = new DataJshandyManServices();

    if (data !== undefined && data != null && configurationRedy) {
      this._dataJshandyManServices = data;
    }

    // let seccion = new LogingResponse();
    // seccion = this.SessionService.get('UserSession');
    // this.setloginAuht(seccion);
    // if (this._loguin !== undefined && this._loguin != null && this._loguin.configurationRedy) {
    //   this._dataJshandyManServices.direction = this._loguin.data.direction;
    //   this._dataJshandyManServices.mail = this._loguin.data.mail;
    //   this._dataJshandyManServices.web = this._loguin.data.web;
    //   this._dataJshandyManServices.phoneNumber = this._loguin.data.phoneNumber;
    //   this._dataJshandyManServices.taxRegNumber = this._loguin.data.taxRegNumber;
    //   this._dataJshandyManServices.coments1 = this._loguin.data.coments1;
    //   this._dataJshandyManServices.coments2 = this._loguin.data.coments2;
    //   this._dataJshandyManServices.coments3 = this._loguin.data.coments3;
    //   this._dataJshandyManServices.coments4 = this._loguin.data.coments4;
    //   this._dataJshandyManServices.portMail = this._loguin.data.portMail;
    //   this._dataJshandyManServices.company = this._loguin.data.company;
    //   this._dataJshandyManServices.deadLineAlert = this._loguin.data.deadLineAlert;
    // }

  }

}

