import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DashboardData } from '../models/dashboard-data.model';
import { WorkListTabletPojo } from '../models/workListTabletPojo.model';
import { SessionStorageService } from './session-storage.service';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

 private root = 'api/Dashboard';

 public dashboardData$ = new BehaviorSubject<DashboardData>(new DashboardData());
 evento = this. dashboardData$.asObservable();

 dataListWork2 = new Array<WorkListTabletPojo>();

  public alertData$ = new BehaviorSubject<Array<WorkListTabletPojo>>(new Array<WorkListTabletPojo>());
  eventoAlert = this. alertData$.asObservable();

  alert$ = new EventEmitter<Array<WorkListTabletPojo>>();


  constructor(protected http: HttpClient, private router: Router, private SessionService: SessionStorageService) { }

  updatedDashboardData(data: DashboardData): void {
    this.dashboardData$.next(data);
    this.updatedAlertData(data.worksBeforeDedLineAlert);
  }

  updatedAlertData(data: Array<WorkListTabletPojo>): void {
    this.alertData$.next(data);
    this.alert$.emit(data);
   }

  getDashboardData(): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `${this.root}/data`, opciones);
  }


  getDashboardSendMailData(): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `${this.root}/data/send/mail`, opciones);
  }

}
