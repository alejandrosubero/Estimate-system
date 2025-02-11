import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { EmailsPojo } from 'src/app/models/emails.model';
import { MatChipInputEvent } from '@angular/material/chips';
import { FormBuilder, Validators } from '@angular/forms';
import { Estimate } from 'src/app/models/estimate.model';
import { Work } from 'src/app/models/work.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CoreService } from 'src/app/services/core.service';
import { EmailHandyManTally } from 'src/app/models/email-handy-man-tally.model';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';


@Component({
  selector: 'app-send-mail',
  templateUrl: './send-mail.component.html',
  styleUrls: ['./send-mail.component.css']
})
export class SendMailComponent implements OnInit, OnChanges {

  @Input() estimatePojo: Estimate;
  @Input() workPojo: Work;
  @Input() emailHandyManTally: EmailHandyManTally;
  @Output() sendEvent: EventEmitter<number> = new EventEmitter();

  mails: EmailsPojo = new EmailsPojo();
  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;


  mailForm = this.fb.group({
    emailsTo: null,
    emailsCc: null,
    emailsBcc: [null],
    subject: [null, Validators.required],
    content: [''],
  });

  // tslint:disable-next-line: typedef
  get content() { return this.mailForm.get('content'); }
  boddy: any;

  constructor(
    private fb: FormBuilder,
    private _snackBar: MatSnackBar,
    private coreService: CoreService,
    private dialog: MatDialog) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.getCliente();
  }

  ngOnInit(): void {
    this.getCliente();
  }


  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action);
  }

  getCliente(): void {
    if (this.estimatePojo !== undefined && this.estimatePojo != null
      && this.estimatePojo.idEstimate !== undefined && this.estimatePojo.idEstimate != null) {
      this.boddy = this.estimatePojo;
      this.emailHandyManTally.estimatePojo = this.estimatePojo;
      this.emailHandyManTally.subject = '';
      this.emailHandyManTally.subject = `${this.emailHandyManTally.subject} - Estimate number: ${this.estimatePojo.idEstimate}`;
      this.mails = new EmailsPojo();
      this.estimatePojo.client.emails.forEach(mailsCliente => {
        this.mails.to.push(mailsCliente.email);
      });
    }

    if (this.workPojo !== undefined && this.workPojo != null && this.workPojo.idWork !== undefined && this.workPojo.idWork != null) {
      this.boddy = this.workPojo;
      this.emailHandyManTally.workPojo = this.workPojo;
      this.emailHandyManTally.subject = '';
      this.emailHandyManTally.subject = `${this.emailHandyManTally.subject} - Invoice number: ${this.workPojo.idWork}`;
      this.mails = new EmailsPojo();
      this.workPojo.client.emails.forEach(mailsCliente => {
        this.mails.to.push(mailsCliente.email);
      });
    }
  }

  comentText(): void {
    this.mails.content = this.mailForm.value.content;
  }

  subjectText(): void {
    this.mails.subject = this.mailForm.value.subject;
  }


  addMailTo(event: MatChipInputEvent): void {
    this.addMail(event, this.mails.to);
  }

  removeTo(mail: string): void {
    this.remove(mail, this.mails.to);
  }

  addMailBcc(event: MatChipInputEvent): void {
    this.addMail(event, this.mails.bcc);
  }

  removeBcc(mail: string): void {
    this.remove(mail, this.mails.bcc);
  }

  addMailCc(event: MatChipInputEvent): void {
    this.addMail(event, this.mails.cc);
  }

  removeCc(mail: string): void {
    this.remove(mail, this.mails.cc);
  }

  remove(mail: string, list: Array<string>): void {
    const index = list.indexOf(mail);
    if (index >= 0) {
      list.splice(index, 1);
    }
  }


  addMail(event: MatChipInputEvent, list: Array<string>): void {
    const regex = new RegExp('([!#-\'*+/-9=?A-Z^-~-]+(\.[!#-\'*+/-9=?A-Z^-~-]+)*|"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+")@([!#-\'*+/-9=?A-Z^-~-]+(\.[!#-\'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])');
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      if (regex.test(value.trim())) {
        let mail: string = '';
        mail = value.trim();
        list.push(mail);
      } else {
        alert('Enter an email with Valid Format');
        if (input) { input.value = ''; }
      }
    }
    if (input) { input.value = ''; }
  }


  onSent(): void {
    if (this.mails.to.length > 0) {
      if (!this.mailForm.valid) {
        this.notification(true);
        return;
      }
      this.send();
    } else {
      this.notification(false);
    }
  }


  send(): void {
    this.emailHandyManTally.email = this.mails;
    this.emailHandyManTally.subject = '';
    this.emailHandyManTally.subject = this.mails.subject;
    this.coreService.sendWork(this.emailHandyManTally).subscribe(response => {
      this.sendEvent.emit(0);
    });
  }


  notification(action: boolean): void {

    let titles = '';
    let message = '';

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '45%';
    dialogConfig.height = 'auto';

    if (action) {
      titles = ' Subject Alert';
      message = 'You have not added a subject to the mail! - Do you want to send it without subject?';
      dialogConfig.data = {title: titles, msg: message, cancel: 'Cancel', confirm: 'Confirm'};
    }

    if (!action) {
      titles = ' Recipient Alert';
      message = 'The recipient is required to send the mail';
      dialogConfig.data = {title: titles, msg: message, cancel: 'ok'};
    }


    const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(response => {

      if (response) {
        this.mails.subject = this.emailHandyManTally.subject;
        this.send();
      }

      if (!response) {
        return;
      }
    });
  }




}
