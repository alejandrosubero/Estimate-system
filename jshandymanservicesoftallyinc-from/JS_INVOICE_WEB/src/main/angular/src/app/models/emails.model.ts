export class EmailsPojo {

    to: Array<string>;
    cc: Array<string>;
    bcc: Array<string>;

    from: string;
    subject: string;
    content: string;
    body: string;
    adjunto: any;

    constructor() {
        this.to = new Array<string>();
        this.cc = new Array<string>();
        this.bcc = new Array<string>();
    }

}
