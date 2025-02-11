import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Bill } from '../models/bill.model';
import { Client } from '../models/client.model';
import { EstimateListTabletPojo } from '../models/estimate-list-tablet-pojo.model';
import { Estimate } from '../models/estimate.model';
import { MailCliente } from '../models/mail-cliente.model';
import { PhoneClient } from '../models/phone-client.model';
import { Product } from '../models/product.model';
import { ServiceHandyManTally } from '../models/service-handy-man-tally.model';
import { Subcontractor } from '../models/subcontractor.model';

@Injectable({
  providedIn: 'root'
})
export class EstimateService {

  private estimateList = new BehaviorSubject<Array<Estimate>>(new Array<Estimate>());
  private estimateList2 = new BehaviorSubject<Array<EstimateListTabletPojo>>(new Array<EstimateListTabletPojo>());
  em = this.estimateList.asObservable();
  em2 = this.estimateList2.asObservable();
  startClient = false;

  isEstimate: Estimate;

  constructor(protected http: HttpClient) { }

  updateEstimateList(newEstimate: Estimate[]): void {
    this.estimateList.next(newEstimate);
  }

  updateEstimateList2(newEstimateListTablet: EstimateListTabletPojo[]): void {
    this.estimateList2.next(newEstimateListTablet);
  }

  // tslint:disable-next-line: typedef
  findEstimate(id: any) {
    let lista: Array<Estimate> = new Array<Estimate>();
    this.em.subscribe(list => {
      lista = list.filter(x => x.idEstimate == id);
    });
    return lista;
  }


  updateEstimate(newEstimate: Estimate): void {
    let es = new Array<Estimate>();
    this.em.subscribe(list => {
      es = list;
    });
    es.push(newEstimate);
    this.updateEstimateList(es);
  }


  // http://localhost:8090/jshandyman/api/estimate/All
  getAllEstimate(): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `api/estimate/All`, opciones);
  }


  // http://localhost:8090/jshandyman/api/estimate/All/list
  getListEstimate(): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `api/estimate/All/list`, opciones);
  }


  // http://localhost:8090/jshandyman/api/work/estimate/{idEstimate}
  checkInvoiceToEstimate(idEstimate: number): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `api/work/estimate/${idEstimate}`, opciones);
  }


  // http://localhost:8090/jshandyman/api/estimate/id/2
  getEsimateById(id: number): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `api/estimate/id/${id}`, opciones);
  }


   // http://localhost:8090/jshandyman/api/estimate/delete/logical/2
   deleteEsimate(id:String): Observable<any> {
    const opciones = {
      headers: new HttpHeaders(),
    };
    return this.http.get(environment.serverUrl + `api/estimate/delete/logical/${id}`, opciones);
  }


  // http://localhost:8090/jshandyman/api/estimate/new/save
  saveEstimate(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(environment.serverUrl + 'api/estimate/new/save', body, { headers });
  }


  // http://localhost:8090/jshandyman/api/estimate/new/save/update
  saveAndUpdateEstimate(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(environment.serverUrl + 'api/estimate/new/save/update', body, { headers });
  }


  // http://localhost:8090/jshandyman/api/estimate/covertEstimate
  covertEstimate(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(environment.serverUrl + 'api/estimate/covertEstimate', body, { headers });
  }


  round(num): number {
    const numero = Number((Math.abs(num) * 100).toPrecision(15));
    return Math.round(numero) / 100 * Math.sign(num);
  }


  copyEstimate(estimate: Estimate, cli: boolean): Estimate {
    const newEstimate = new Estimate();

    if (cli) {
      newEstimate.client = this.copyClient(estimate);
    }

    newEstimate.description = estimate.description;
    newEstimate.starDate = estimate.starDate;
    newEstimate.overhead = estimate.overhead;
    newEstimate.totalCostWorkWithoutTaxes = estimate.totalCostWorkWithoutTaxes;
    newEstimate.totalCostWork = estimate.totalCostWork;
    newEstimate.title = estimate.title;
    newEstimate.owner = estimate.owner;
    newEstimate.createDay = new Date();
    if (estimate.bills !== undefined && estimate.bills != null && estimate.bills.length > 0) {
      newEstimate.bills = this.copyBill(estimate.bills);
    }
    if (estimate.subcontractors !== undefined && estimate.subcontractors != null && estimate.subcontractors.length > 0) {
      newEstimate.subcontractors = this.copySubcontractors(estimate.subcontractors);
    }
    if (estimate.services !== undefined && estimate.services != null && estimate.services.length > 0) {
      newEstimate.services = this.copyService(estimate.services);
    }
    return newEstimate;
  }

  private copyClient(estimate: Estimate): Client {

    const newEstimateclient = new Client();
    newEstimateclient.address = estimate.client.address;
    newEstimateclient.city = estimate.client.city;
    newEstimateclient.codeClient = estimate.client.codeClient;
    newEstimateclient.fullAdress = estimate.client.fullAdress;
    newEstimateclient.lastName = estimate.client.lastName;
    newEstimateclient.name = estimate.client.name;
    newEstimateclient.state = estimate.client.state;
    newEstimateclient.zipCode = estimate.client.zipCode;

    if (estimate.client.phoneNumbers !== undefined && estimate.client.phoneNumbers != null && estimate.client.phoneNumbers.length > 0) {
      estimate.client.phoneNumbers.forEach(phoneNumber => {
        let phone = new PhoneClient();
        phone.number = phoneNumber.number;
        phone.codeClient = phoneNumber.codeClient;
        newEstimateclient.phoneNumbers.push(phone);
      });
    }
    if (estimate.client.emails !== undefined && estimate.client.emails != null && estimate.client.emails.length > 0) {
      estimate.client.emails.forEach(email => {
        let mail = new MailCliente();
        mail.email = email.email;
        mail.codeClient = email.codeClient;
        newEstimateclient.emails.push(mail);
      });
    }
    return newEstimateclient;
  }


  private copySubcontractors(sub: Array<Subcontractor>): Array<Subcontractor> {
    const listaSubcontractor = new Array<Subcontractor>();
    sub.forEach(subcontrac => {
      const newSubcontractor = new Subcontractor();
      newSubcontractor.company = subcontrac.company;
      newSubcontractor.phoneNumber = subcontrac.phoneNumber;
      newSubcontractor.mail = subcontrac.mail;
      newSubcontractor.description = subcontrac.description;
      newSubcontractor.costOfwork = subcontrac.costOfwork;
      newSubcontractor.totalCost = subcontrac.totalCost;
      newSubcontractor.profit = subcontrac.profit;
      newSubcontractor.billListSubcontractor = new Array<Bill>();
      // tslint:disable-next-line: max-line-length
      if (subcontrac.billListSubcontractor !== undefined && subcontrac.billListSubcontractor != null && subcontrac.billListSubcontractor.length > 0) {
        newSubcontractor.billListSubcontractor = this.copyBill(subcontrac.billListSubcontractor);
      }
      listaSubcontractor.push(newSubcontractor);
    });
    return listaSubcontractor;
  }




  private copyBill(billList: Array<Bill>): Array<Bill> {
    const newEstimateBills = new Array<Bill>();
    billList.forEach(bill => {
      const newBill = new Bill();
      newBill.billNumber = bill.billNumber;
      newBill.billType = bill.billType;
      newBill.description = bill.description;
      newBill.billTotal = bill.billTotal;
      newBill.billTotalWichoutTaxes = bill.billTotalWichoutTaxes;
      newBill.productsAndServices = new Array<Product>();
      if (bill.productsAndServices !== undefined && bill.productsAndServices != null && bill.productsAndServices.length > 0) {
        bill.productsAndServices.forEach(product => {
          const newProduct = new Product();
          newProduct.productCode = product.productCode;
          newProduct.name = product.name;
          newProduct.categories = product.categories;
          newProduct.description = product.description;
          newProduct.notas = product.notas;
          newProduct.price = product.price;
          newProduct.priceWithTaxes = product.priceWithTaxes;
          newProduct.stockNumber = product.stockNumber;
          newProduct.totalPriceWithTaxes = product.totalPriceWithTaxes;
          newProduct.totalPriceWithOupTaxes = product.totalPriceWithOupTaxes;
          newBill.productsAndServices.push(newProduct);
        });
      }
      newEstimateBills.push(newBill);
    });
    return newEstimateBills;
  }


  private copyService(servicesList: Array<ServiceHandyManTally>): Array<ServiceHandyManTally> {
    const services = new Array<ServiceHandyManTally>();
    servicesList.forEach(service => {
      const serv = new ServiceHandyManTally();
      serv.servicesCost = service.servicesCost;
      serv.overhead = service.overhead;
      serv.descriptionOfServicesCost = service.descriptionOfServicesCost;
      services.push(serv);
    });
    return services;
  }



}


// estimate.bills.forEach(bill => {
      //   let newBill = new Bill();
      //   newBill.billNumber = bill.billNumber;
      //   newBill.billType = bill.billType;
      //   newBill.description = bill.description;
      //   newBill.billTotal = bill.billTotal;
      //   newBill.billTotalWichoutTaxes = bill.billTotalWichoutTaxes;
      //   newBill.productsAndServices = new Array<Product>();
      //   if (bill.productsAndServices !== undefined && bill.productsAndServices != null && bill.productsAndServices.length > 0) {
      //     bill.productsAndServices.forEach(product => {
      //       let newProduct = new Product();
      //       newProduct.productCode = product.productCode;
      //       newProduct.name = product.name;
      //       newProduct.categories = product.categories;
      //       newProduct.description = product.description;
      //       newProduct.notas = product.notas;
      //       newProduct.price = product.price;
      //       newProduct.priceWithTaxes = product.priceWithTaxes;
      //       newProduct.stockNumber = product.stockNumber;
      //       newProduct.totalPriceWithTaxes = product.totalPriceWithTaxes;
      //       newProduct.totalPriceWithOupTaxes = product.totalPriceWithOupTaxes;
      //       newBill.productsAndServices.push(newProduct);
      //     });
      //   }
      //   newEstimate.bills.push(newBill);
      // });