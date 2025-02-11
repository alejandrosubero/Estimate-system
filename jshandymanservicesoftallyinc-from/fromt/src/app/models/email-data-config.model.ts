export class EmailDataConfig {
    host: string;
    mailpassword: string;
    mailusername: string;
    port: string;

    constructor() {
        this.host = 'smtp.gmail.com';
        this.port = '587';
    }
}
