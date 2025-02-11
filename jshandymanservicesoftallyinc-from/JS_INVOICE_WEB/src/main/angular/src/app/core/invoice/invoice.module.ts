import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ViewInvoiceComponent } from './view-invoice/view-invoice.component';
import { ShareModule } from 'src/app/share/share.module';

import { InvoiceComponent } from './invoice-list/invoice.component';
import { MaterialModule } from 'src/app/materialModule/materialModule';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { InvoiceRoutingModule } from './invoice.routing';
import { AuthGuardService } from 'src/app/services/auth-guard-service.service';
import { EdidInvoiceComponent } from './edid-invoice/edid-invoice.component';
import { ViewInvoiceAvanceComponent } from './view-invoice-avance/view-invoice-avance.component';
import { InvoiceAlertComponent } from './invoice-list-alert/invoice-alert.component';

@NgModule({
  declarations: [
    InvoiceComponent,
    ViewInvoiceComponent,
    EdidInvoiceComponent,
    ViewInvoiceAvanceComponent,
    InvoiceAlertComponent
  ],
  imports: [
    CommonModule,
    ShareModule,
    MaterialModule,
    NgbModule,
    InvoiceRoutingModule,
  ],
  providers: [
    AuthGuardService,
  ],
})
export class InvoiceModule { }
