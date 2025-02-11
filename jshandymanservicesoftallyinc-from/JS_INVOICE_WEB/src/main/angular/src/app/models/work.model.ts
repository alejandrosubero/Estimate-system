import { Bill } from './bill.model';
import { Client } from './client.model';
import { Payment } from './payment.model';
import { ServiceHandyManTally } from './service-handy-man-tally.model';
import { Subcontractor } from './subcontractor.model';

export class Work {

    idWork: number;
    codeWork: string;
    description: string;
    dedline: Date;
    starDate: Date;
    daysToDeline: number;
    daysLate: number;
    totalCostWorkWithoutTaxes: number;
    totalCostWork: number;
    remainingPayable: number;
    totalAmountPaind: number;
    overhead: number;
    idEstimate: number;
    client: Client;

    payments: Array<Payment>;
    bills: Array<Bill>;
    subcontractors: Array<Subcontractor>;
    services: Array<ServiceHandyManTally>;

    title: string;
    createDay: Date;
    owner: string;
    active: boolean;
    status: string;
    company:string;



    constructor() {
        this.payments = new Array<Payment>();
        this.bills = new Array<Bill>();
        this.subcontractors = new Array<Subcontractor>();
        this.services = new Array<ServiceHandyManTally>();
    }
}
