import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SessionStorageService } from './session-storage.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Client } from '../models/client.model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(protected http: HttpClient, private router: Router, private SessionService: SessionStorageService) { }


  private clientList = new BehaviorSubject<Array<Client>>(new Array<Client>());
  em2 = this.clientList.asObservable();

  updateClientList(newList: Client[]): void {
    this.clientList.next(newList);
  }


  getMapClients(): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `api/client/map/all`, opciones);
  }

  getClients(): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `api/client/active/all`, opciones);
  }

  resetAndGetClients(): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `api/client/reset/Clients`, opciones);
  }
  
}
