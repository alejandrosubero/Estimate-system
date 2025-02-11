import { Byte } from '@angular/compiler/src/util';

export class Email {

    to: string;
    from: string;
    subject: string;
    content: string;
    template: string;
    adjunto: Byte[];

}

