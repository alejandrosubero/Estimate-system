import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Work } from '../models/work.model';
import { WorkListTabletPojo } from '../models/workListTabletPojo.model';

@Injectable({
  providedIn: 'root'
})
export class InvoiceWorkService {

  private workList = new BehaviorSubject<Array<Work>>(new Array<Work>());
  private workList2 = new BehaviorSubject<Array<WorkListTabletPojo>>(new Array<WorkListTabletPojo>());
  private workListDedline = new BehaviorSubject<Array<WorkListTabletPojo>>(new Array<WorkListTabletPojo>());
  private workListDetail = new BehaviorSubject<Array<WorkListTabletPojo>>(new Array<WorkListTabletPojo>());
  em = this.workList.asObservable();
  em2 = this.workList2.asObservable();
  em2Detail = this.workListDetail.asObservable();
  emDedline = this.workListDedline.asObservable();

  startClient = false;
  isEstimate: Work;
  apiWorkUrl = 'api/work/';

  constructor(protected http: HttpClient) { }

  updateWorkList(newEstimate: Work[]): void {
    this.workList.next(newEstimate);
  }

  updateWorkList2(newWorkListTablet: WorkListTabletPojo[]): void {
    this.workList2.next(newWorkListTablet);
  }

  updateWorkListDetail(newWorkListTabletDetail: WorkListTabletPojo[]): void {
    this.workListDetail.next(newWorkListTabletDetail);
  }

  updateworkListDedline(newListTablet: WorkListTabletPojo[]): void {
    this.workListDedline.next(newListTablet);
  }

  


  findWork(id: any): Array<Work> {
    let lista: Array<Work> = new Array<Work>();
    this.em.subscribe(list => {
      lista = list.filter(x => x.idEstimate == id);
    });
    return lista;
  }


  updateWork(newWork: Work): void {
    let es = new Array<Work>();
    this.em.subscribe(list => {
      es = list;
    });
    es.push(newWork);
    this.updateWorkList(es);
  }


  saveWork(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(`${environment.serverUrl}${this.apiWorkUrl}new/save`, body, { headers });
  }

  saveAndUpdateWork(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(`${environment.serverUrl}${this.apiWorkUrl}new/save/update`, body, { headers });
  }


  getList(): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(`${environment.serverUrl}${this.apiWorkUrl}All/list`, opciones);
  }


  getWorkById(id: number): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(`${environment.serverUrl}${this.apiWorkUrl}work/id/${id}`, opciones);
  }


  deleteWork(id: String): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(`${environment.serverUrl}${this.apiWorkUrl}delete/logical/${id}`, opciones);
  }


}
