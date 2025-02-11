export class Taxes {

    description: string;
    discount: number;
    discountcashpayment: number;
    overHead: number;
    taxes: number;

    constructor() {
        this.discount = 0;
        this.discountcashpayment = 0;
        this.overHead = 10;
        this.taxes = 0;
    }

}
