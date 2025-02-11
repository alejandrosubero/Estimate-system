import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Bill } from 'src/app/models/bill.model';
import { DataJshandyManServices } from 'src/app/models/DataJshandyManServices.model';
import { Estimate } from 'src/app/models/estimate.model';
import { Product } from 'src/app/models/product.model';
import { CoreService } from 'src/app/services/core.service';


@Component({
  selector: 'app-view-estimate',
  templateUrl: './view-estimate.component.html',
  styleUrls: ['./view-estimate.component.css']
})
export class ViewEstimateComponent implements OnInit, OnChanges {

  @Input() estimateView: Estimate;
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
  public tipoForm = 'Estimate';
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
    if (this.estimateView !== undefined && this.estimateView != null) {
      this.vistaName = `Estimate `;
      // TODO: CONSULTAR ESTE PUNTO SI VA A SER UN NEW DATE
      this.todayDate = this.estimateView.createDay;
      this.showOverhead = true;
      this.setSubContractorsProfitCalculationForView();
      this.startView();
    }
  }


  setSubContractorsProfitCalculationForView(): void {

    // tslint:disable-next-line: max-line-length
    if (this.estimateView.subcontractors != null && this.estimateView.subcontractors !== undefined && this.estimateView.subcontractors.length > 0) {

      let totalcostForProfitCalculation = 0;
      let subcontractorsTotalCostOfwork = 0;

      this.estimateView.subcontractors.forEach(subcontractors => {
          subcontractorsTotalCostOfwork += subcontractors.costOfwork;

          if (subcontractors.billListSubcontractor !== undefined
                        && subcontractors.billListSubcontractor != null
                        && subcontractors.billListSubcontractor.length > 0) {

            subcontractors.billListSubcontractor.forEach(billsub => {
              totalcostForProfitCalculation += billsub.billTotal;
            });
          }

          const subcontractorsTotalProfit = (subcontractors.costOfwork + totalcostForProfitCalculation) * (subcontractors.profit / 100);
          subcontractors.profitCalculation = this.coreService.round(subcontractorsTotalProfit);

      });
    }
  }



  startView(): void {
    // tslint:disable-next-line: max-line-length
    if (this.estimateView.subcontractors !== undefined && this.estimateView.subcontractors != null && this.estimateView.subcontractors.length > 0) {
      this.showProfit = true;
    }
    this.fillProductInBillTablet();
    this.fillBillTablet();
  }


  ngOnChanges(changes: SimpleChanges): void {
    this.setSubContractorsProfitCalculationForView();
    this.startView();
    // console.log(this.estimateView);
  }


  sedDataClientInView(estimate: Estimate): void {
    const name = `${this.estimateView.client.name} ${this.estimateView.client.lastName}`;
    this.nameCliente = ` ${name}`;
    this.addressCliente = ` ${this.estimateView.client.fullAdress}`;
  }


  calculateOverhead(valor: number): number {
    let resultado: number;
    if (this.estimateView.overhead !== undefined && this.estimateView.overhead != null) {
      resultado = valor * (this.estimateView.overhead / 100);
      return resultado;
    } else {
      return valor;
    }
  }

  fillProductInBillTablet(): void {
    this.listProduct = new Array<Product>();

    this.estimateView.bills.forEach(bill => {
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

    this.estimateView.bills.forEach(bill => {

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


}




