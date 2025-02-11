import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from 'src/app/services/auth-guard-service.service';
import { EdidInvoiceComponent } from './edid-invoice/edid-invoice.component';
import { InvoiceComponent } from './invoice-list/invoice.component';



const routes: Routes = [
    { path: 'list', component: InvoiceComponent, canActivate: [AuthGuardService] },
    { path: 'edid-and-detail', component: EdidInvoiceComponent, canActivate: [AuthGuardService] },
    { path: 'new', component: EdidInvoiceComponent, canActivate: [AuthGuardService] }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class InvoiceRoutingModule { }


// jshandy-man-services/menu/invoices/invoice-list
// jshandy-man-services/menu/invoices/invoice-list