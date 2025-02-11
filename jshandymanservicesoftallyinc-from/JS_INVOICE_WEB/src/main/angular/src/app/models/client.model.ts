import { MailCliente } from './mail-cliente.model';
import { PhoneClient } from './phone-client.model';

export class Client {

    idClient: number;
    codeClient: string;
    name: string;
    lastName: string;
    address: string;
    state: string;
    zipCode: string;
    fullName: string;
    fullAdress: string;
    phoneNumbers: Array<PhoneClient>;
    emails: Array<MailCliente>;
    city: string; // no implementado en el back
    codeWork: string;
    active: boolean;
    estimateId: number;
    workId: number;
    from: string;

    constructor() {
        this.from = '';
        this.phoneNumbers = new Array<PhoneClient>();
        this.emails = new Array<MailCliente>();
    }

}
