import { Bill } from './bill.model';

export class Subcontractor {

    company: string;
    phoneNumber: string;
    mail: string;
    description: string;
    codeClient: string;

    costOfwork: number;
    totalCost: number;
    profit: number;

    idSubContractor: number;
    idWork: number;
    idEstimate: number;

    dateOfWork: Date;
    datePain: Date;
    billListSubcontractor: Array<Bill>;
    itemDeliteEdit: boolean;

    profitCalculation: number;
    referenceEstimate: number;

    constructor(){
        this.billListSubcontractor = new Array<Bill>();
        this.profitCalculation = 0;
    }
}

