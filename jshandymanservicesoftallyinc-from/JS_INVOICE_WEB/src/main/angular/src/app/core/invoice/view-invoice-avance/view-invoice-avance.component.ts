import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Bill } from 'src/app/models/bill.model';
import { DataJshandyManServices } from 'src/app/models/DataJshandyManServices.model';
import { Estimate } from 'src/app/models/estimate.model';
import { Product } from 'src/app/models/product.model';
import { Work } from 'src/app/models/work.model';
import { CoreService } from 'src/app/services/core.service';

@Component({
  selector: 'app-view-invoice-avance',
  templateUrl: './view-invoice-avance.component.html',
  styleUrls: ['./view-invoice-avance.component.css']
})
export class ViewInvoiceAvanceComponent implements OnInit, OnChanges {

  @Input() invoiceView: Work;
  @Input() printProduct: boolean;

  @Input() billTotalOverhead: number;
  @Input() servicesTotalOverhead: number;
  @Input() profitCalculation: number;

  @Input() billLastTotal: number;
  @Input() servicesTotal: number;
  @Input() subcontractorsTotal: number;

  @Input() dataJshandyManServices: DataJshandyManServices;

  vistaName: string;
  public nameCliente: string;
  public addressCliente: string;
  public tipoForm = 'Invoice';
  public for = 'For:';

  billQuantity = 1;
  serviceQuantity = 1;
  productQuantity = 1;
  subContractortsQuantity = 1;

  todayDate = new Date();
  line1 = [];

  showOverhead: boolean;
  showProfit: boolean;

  listbills: Array<Bill> = new Array<Bill>();
  listProduct: Array<Product> = new Array<Product>();



  constructor(public coreService: CoreService) { }



  ngOnInit(): void {
    if (this.invoiceView !== undefined && this.invoiceView != null) {
      this.vistaName = `Invoice `;
      // TODO: CONSULTAR ESTE PUNTO SI VA A SER UN NEW DATE
      this.todayDate = this.invoiceView.createDay;
      this.showOverhead = true;
      this.startView();
    }
  }


  startView(): void {
    // tslint:disable-next-line: max-line-length
    if (this.invoiceView.subcontractors !== undefined && this.invoiceView.subcontractors != null && this.invoiceView.subcontractors.length > 0) {
      this.showProfit = true;
    }
    this.fillProductInBillTablet();
    this.fillBillTablet();
  }


  ngOnChanges(changes: SimpleChanges): void {
    this.startView();
  }


  sedDataClientInView(estimate: Estimate): void {
    const name = `${this.invoiceView.client.name} ${this.invoiceView.client.lastName}`;
    this.nameCliente = ` ${name}`;
    this.addressCliente = ` ${this.invoiceView.client.fullAdress}`;
  }


  calculateOverhead(valor: number): number {
    let resultado: number;
    if (this.invoiceView.overhead !== undefined && this.invoiceView.overhead != null) {
      resultado = valor * (this.invoiceView.overhead / 100);
      return resultado;
    } else {
      return valor;
    }
  }

  fillProductInBillTablet(): void {
    this.listProduct = new Array<Product>();

    this.invoiceView.bills.forEach(bill => {
      if (this.printProduct && bill.productsAndServices !== undefined
        && bill.productsAndServices != null && bill.productsAndServices.length > 0) {
        Array.prototype.push.apply(this.listProduct, bill.productsAndServices);
      }
    });
  }



  fillBillTablet(): void {
    let totalOfBillsWichoutTaxes = 0;
    let totalOfBills = 0;
    this.listbills = new Array<Bill>();

    this.invoiceView.bills.forEach(bill => {

      if (!this.printProduct) {
        totalOfBillsWichoutTaxes += bill.billTotalWichoutTaxes;
        totalOfBills += bill.billTotal;
        this.listbills.push(bill);
      }

      if (!bill.itemDeliteEdit && this.printProduct
        && bill.productsAndServices !== undefined && bill.productsAndServices != null
        && bill.productsAndServices.length == 0) {
        totalOfBillsWichoutTaxes += bill.billTotalWichoutTaxes;
        totalOfBills += bill.billTotal;
        this.listbills.push(bill);
      }

      if (bill.itemDeliteEdit && this.printProduct
        && bill.productsAndServices !== undefined && bill.productsAndServices != null
        && bill.productsAndServices.length == 0) {
        this.listbills.push(bill);
      }
    });
  }


  totalRemainingPayable(total: number): number {
    let amountPaid = 0;
    this.invoiceView.payments.forEach(pay => {
      amountPaid += pay.amountPaind;
    });
    return this.coreService.round(total - amountPaid);
  }

}

