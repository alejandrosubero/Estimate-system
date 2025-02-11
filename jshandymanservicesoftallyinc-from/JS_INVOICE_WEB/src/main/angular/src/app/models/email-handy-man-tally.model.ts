import { EmailsPojo } from './emails.model';
import { Estimate } from './estimate.model';
import { Work } from './work.model';

export class EmailHandyManTally {

    subject: string;
    template: string;
    printProduct: boolean;
    avancePayments: boolean;
    workPojo: Work;
    estimatePojo: Estimate;
    email: EmailsPojo;
    

    // private EmailDataConfigPojo Emailconfiguration;






    constructor(){
        this.email = new EmailsPojo();
        this.printProduct = false;
        this.avancePayments = false;
    }
}
