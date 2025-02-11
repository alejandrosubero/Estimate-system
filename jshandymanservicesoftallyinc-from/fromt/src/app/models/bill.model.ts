import { Product } from './product.model';

export class Bill {

    billNumber: string;
    billType: string;
    description: string;
    billTotal: number;
    billTotalWichoutTaxes: number;
    productsAndServices: Array<Product>;

    idWork: number;
    idEstimate: number;
    idBill: number;
    idSubContractor: number;
    itemDeliteEdit: boolean;
    referenceEstimate: number;
    constructor(){
       this.productsAndServices = new Array<Product>();
    }
}


/*
{
          "idSubContractor": null,
          "description": "materiales en general",
          "productsAndServices": [
            {
              "name": "panel de yeso ACME",
              "price": 490,
              "totalPriceWithTaxes": 1000,
              "categories": "panel",
              "description": "panel de yeso",
              "notas": "...",
              "idBill": 3026,
              "priceWithTaxes": 500,
              "totalPriceWithOupTaxes": 900,
              "idproduct": 4026,
              "productcode": "aaa33",
              "stocknumber": 2
            }
          ],
          "idWork": null,
          "idEstimate": 1,
          "idbill": 3026,
          "billtype": "compras",
          "billnumber": "A1123",
          "billtotalwichouttaxes": 900,
          "billtotal": 1000
        },
*/