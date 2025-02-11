import { Component, Inject, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipInputEvent } from '@angular/material/chips';
import { PhoneClient } from 'src/app/models/phone-client.model';
import { MailCliente } from 'src/app/models/mail-cliente.model';
import { Client } from 'src/app/models/client.model';


// tslint:disable-next-line: typedef
export function emailValidator(control: AbstractControl) {
  // tslint:disable-next-line: prefer-const
  var EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;
  if (!EMAIL_REGEXP.test(control.value)) {
    return { invalidEmail: true };
  }
  return null;
}


@Component({
  selector: 'app-new-cliente',
  templateUrl: './new-cliente.component.html',
  styleUrls: ['./new-cliente.component.css']
})
export class NewClienteComponent implements OnInit {


  addressForm = this.fb.group({

    firstName: [null, Validators.required],
    lastName: [null, Validators.required],
    address: [null, Validators.required],
    state: [null, Validators.required],
    city: [null, Validators.required],
    postalCode: [null, Validators.compose([
      Validators.required, Validators.minLength(5), Validators.maxLength(5)])
    ],
    emails: null,
    address2: null,
    codigocliente: [null],
    phone: [null],
  });


  hasUnitNumber = false;

  states = [
    { name: 'Alabama', abbreviation: 'AL' },
    { name: 'Alaska', abbreviation: 'AK' },
    { name: 'American Samoa', abbreviation: 'AS' },
    { name: 'Arizona', abbreviation: 'AZ' },
    { name: 'Arkansas', abbreviation: 'AR' },
    { name: 'California', abbreviation: 'CA' },
    { name: 'Colorado', abbreviation: 'CO' },
    { name: 'Connecticut', abbreviation: 'CT' },
    { name: 'Delaware', abbreviation: 'DE' },
    { name: 'District Of Columbia', abbreviation: 'DC' },
    { name: 'Federated States Of Micronesia', abbreviation: 'FM' },
    { name: 'Florida', abbreviation: 'FL' },
    { name: 'Georgia', abbreviation: 'GA' },
    { name: 'Guam', abbreviation: 'GU' },
    { name: 'Hawaii', abbreviation: 'HI' },
    { name: 'Idaho', abbreviation: 'ID' },
    { name: 'Illinois', abbreviation: 'IL' },
    { name: 'Indiana', abbreviation: 'IN' },
    { name: 'Iowa', abbreviation: 'IA' },
    { name: 'Kansas', abbreviation: 'KS' },
    { name: 'Kentucky', abbreviation: 'KY' },
    { name: 'Louisiana', abbreviation: 'LA' },
    { name: 'Maine', abbreviation: 'ME' },
    { name: 'Marshall Islands', abbreviation: 'MH' },
    { name: 'Maryland', abbreviation: 'MD' },
    { name: 'Massachusetts', abbreviation: 'MA' },
    { name: 'Michigan', abbreviation: 'MI' },
    { name: 'Minnesota', abbreviation: 'MN' },
    { name: 'Mississippi', abbreviation: 'MS' },
    { name: 'Missouri', abbreviation: 'MO' },
    { name: 'Montana', abbreviation: 'MT' },
    { name: 'Nebraska', abbreviation: 'NE' },
    { name: 'Nevada', abbreviation: 'NV' },
    { name: 'New Hampshire', abbreviation: 'NH' },
    { name: 'New Jersey', abbreviation: 'NJ' },
    { name: 'New Mexico', abbreviation: 'NM' },
    { name: 'New York', abbreviation: 'NY' },
    { name: 'North Carolina', abbreviation: 'NC' },
    { name: 'North Dakota', abbreviation: 'ND' },
    { name: 'Northern Mariana Islands', abbreviation: 'MP' },
    { name: 'Ohio', abbreviation: 'OH' },
    { name: 'Oklahoma', abbreviation: 'OK' },
    { name: 'Oregon', abbreviation: 'OR' },
    { name: 'Palau', abbreviation: 'PW' },
    { name: 'Pennsylvania', abbreviation: 'PA' },
    { name: 'Puerto Rico', abbreviation: 'PR' },
    { name: 'Rhode Island', abbreviation: 'RI' },
    { name: 'South Carolina', abbreviation: 'SC' },
    { name: 'South Dakota', abbreviation: 'SD' },
    { name: 'Tennessee', abbreviation: 'TN' },
    { name: 'Texas', abbreviation: 'TX' },
    { name: 'Utah', abbreviation: 'UT' },
    { name: 'Vermont', abbreviation: 'VT' },
    { name: 'Virgin Islands', abbreviation: 'VI' },
    { name: 'Virginia', abbreviation: 'VA' },
    { name: 'Washington', abbreviation: 'WA' },
    { name: 'West Virginia', abbreviation: 'WV' },
    { name: 'Wisconsin', abbreviation: 'WI' },
    { name: 'Wyoming', abbreviation: 'WY' }
  ];


  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  phones: PhoneClient[] = [];
  mails: MailCliente[] = [];
  filterName = '';
  client: Client;


  constructor(
    private fb: FormBuilder, 
    private dialogRef: MatDialogRef<NewClienteComponent>, 
    @Inject(MAT_DIALOG_DATA) public data: any,) {
    if (data.edit) {
      this.client = data.atributo;
      this.updateClientView();
    } else {
      this.client = new Client();
    }
  }



  updateClientView(): void {

    this.states.forEach(x => {
      if (x.abbreviation === this.client.state) {
        this.addressForm.patchValue({
          state: x.abbreviation
        });
        return;
      }
    });

    this.addressForm.patchValue({
      firstName: this.client.name,
      lastName: this.client.lastName,
      address: this.client.address,
      postalCode: this.client.zipCode,
      codigocliente: this.client.codeClient,
      city: this.client.city
    });

    // this.addressForm.get('firstName').setValue(this.client.name);
    // this.addressForm.get('lastName').setValue(this.client.lastName);
    // this.addressForm.get('address').setValue(this.client.address);
    // this.addressForm.get('postalCode').setValue(this.client.zipCode);
    // this.addressForm.get('codigocliente').setValue(this.client.codeClient);
    // this.addressForm.get('city').setValue(this.client.city);

    this.phones = this.client.phoneNumbers;
    this.mails = this.client.emails;
  }


  onAddUnitOrAprt2(): void {
    const lista = ['Apartment', 'Unit', 'apt'];
    if (this.addressForm.value.address2 !== undefined && this.addressForm.value.address2 != null) {
      this.client.address = `${this.addressForm.value.address}, # ${this.addressForm.value.address2}`;
    }else{
      this.client.address = `${this.addressForm.value.address}`;
    }
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      const phone = new PhoneClient();
      phone.number = value.trim();
      this.phones.push(phone);
    }

    if (input) {
      input.value = '';
    }
  }

  remove(phone: PhoneClient): void {
    const index = this.phones.indexOf(phone);
    if (index >= 0) {
      this.phones.splice(index, 1);
    }
    // console.log('remove', this.phones);
  }



  addMail(event: MatChipInputEvent): void {
    const regex = new RegExp('([!#-\'*+/-9=?A-Z^-~-]+(\.[!#-\'*+/-9=?A-Z^-~-]+)*|"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+")@([!#-\'*+/-9=?A-Z^-~-]+(\.[!#-\'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])');
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      if (regex.test(value.trim())) {
        const mail = new MailCliente();
        mail.email = value.trim();
        this.mails.push(mail);
      } else {
        alert('Enter an email with Valid Format');
        if (input) { input.value = ''; }
      }
    }

    if (input) { input.value = ''; }
  }

  removeMail(mail: MailCliente): void {
    const index = this.mails.indexOf(mail);
    if (index >= 0) {
      this.mails.splice(index, 1);
    }
  }


  onCancel(): void {
    this.dialogRef.close();
  }


  ngOnInit(): void {
  }


  onSave(): void {
    if (this.addressForm.valid) {
      this.client.name = this.addressForm.value.firstName;
      this.client.lastName = this.addressForm.value.lastName;
      // this.client.address = this.addressForm.value.address;
      this.onAddUnitOrAprt2();
      this.client.state = this.addressForm.value.state;
      this.client.zipCode = this.addressForm.value.postalCode;
      this.client.city = this.addressForm.value.city;
      this.client.fullAdress = `${this.client.address}, ${this.client.city}, ${this.addressForm.value.state}, ${this.client.zipCode}`;
      this.client.emails = this.mails;
      this.client.phoneNumbers = this.phones;
      this.checkCodigocliente();
      // console.log('JSON of client:', JSON.stringify(this.client));
      this.dialogRef.close(this.client);
    } else {
      alert('complete all the required information');
    }
  }


  checkCodigocliente(): void {
    if (this.addressForm.value.codigocliente !== undefined
      && this.addressForm.value.codigocliente !== null
      && this.addressForm.value.codigocliente !== '') {
      this.client.codeClient = this.addressForm.value.codigocliente;
    } else {
      this.generateCodeClient();
    }
    this.setCodigocliente();
  }

  generateCodeClient(): void {
    const numero = Math.floor(Math.random() * (1000000 + 1));
    this.client.codeClient = `${this.addressForm.value.firstName}${this.client.lastName}-${numero}`;
  }

  setCodigocliente(): void {
    if (this.client.codeClient !== undefined
      && this.client.codeClient !== null
      && this.client.codeClient !== '') {

      this.client.phoneNumbers.forEach(phone => {
        phone.codeClient = this.client.codeClient;
      });

      this.client.emails.forEach(mail => {
        mail.codeClient = this.client.codeClient;
      });
    }
  }


}


