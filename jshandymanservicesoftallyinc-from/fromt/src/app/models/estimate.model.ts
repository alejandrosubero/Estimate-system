import { Bill } from './bill.model';
import { Client } from './client.model';
import { Payment } from './payment.model';
import { ServiceHandyManTally } from './service-handy-man-tally.model';
import { Subcontractor } from './subcontractor.model';

export class Estimate {

    idEstimate: number;
    client: Client;
    description: string;
    starDate: Date;
    overhead: number;
    totalCostWorkWithoutTaxes: number;
    totalCostWork: number;

    bills: Array<Bill>;
    subcontractors: Array<Subcontractor>;
    services: Array<ServiceHandyManTally>;

    payments: Array<Payment>;
    codeWork: string;
    dedline: Date;
    daysToDeline: number;
    daysLate: number;
    totalAmountPaind: number;
    remainingPayable: number;
    title: string;
    owner: string;
    createDay: Date;
    active: boolean;
    status: string;

    constructor() {

        this.payments = new Array<Payment>();
        this.bills = new Array<Bill>();
        this.subcontractors = new Array<Subcontractor>();
        this.services = new Array<ServiceHandyManTally>();
    }

}

