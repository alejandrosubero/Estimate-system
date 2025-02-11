import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../materialModule/materialModule';
import { SendMailComponent } from './send-mail/send-mail.component';
import { NewClienteComponent } from './client/new-cliente/new-cliente.component';
import { ClientDetailComponent } from './client/client-detail/client-detail.component';
import { NewProductComponent } from './product/new-product/new-product.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { BillListComponent } from './bill/bill-list/bill-list.component';
import { BillDetailProductListComponent } from './bill/bill-detail-product-list/bill-detail-product-list.component';
import { NewBillComponent } from './bill/new-bill/new-bill.component';
import { SubContractorsListComponent } from './SubContractors/sub-contractors-list/sub-contractors-list.component';
import { SubContractorsDetailComponent } from './SubContractors/sub-contractors-detail/sub-contractors-detail.component';
import { NewSubContractorsComponent } from './SubContractors/new-sub-contractors/new-sub-contractors.component';
import { ServiceListComponent } from './service/service-list/service-list.component';
import { ServiceDetailComponent } from './service/service-detail/service-detail.component';
import { NewServiceComponent } from './service/new-service/new-service.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { AlertMensajeComponent } from './panel-item-modificador/alert-mensaje/alert-mensaje.component';
import { ModificacionAlertComponent } from './panel-item-modificador/modificacion-alert/modificacion-alert.component';
import { PanelItemComponent } from './panel-item-modificador/panel-item/panel-item.component';
import { AppPanelItemProductComponent } from './product/app-panel-item-product/app-panel-item-product.component';
import { AppPanelItemServiceComponent } from './service/app-panel-item-service/app-panel-item-service.component';
import { AppPanelItemBillComponent } from './bill/app-panel-item-bill/app-panel-item-bill.component';
// tslint:disable-next-line: max-line-length
import { AppPanelItemSubcontractorsComponent } from './SubContractors/app-panel-item-subcontractors/app-panel-item-subcontractors.component';
import { NewPaimentComponent } from './paiment/new-paiment/new-paiment.component';
import { PaymentListComponent } from './paiment/payment-list/payment-list.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { SpinnerOverlayComponent } from './spinner-overlay/spinner-overlay.component';




// import {BrowserModule} from '@angular/platform-browser';
// import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
// import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';




@NgModule({
  declarations: [
    SendMailComponent,
    NewClienteComponent,
    ClientDetailComponent,
    NewProductComponent,
    ProductDetailComponent,
    BillListComponent,
    BillDetailProductListComponent,
    NewBillComponent,
    SubContractorsListComponent,
    SubContractorsDetailComponent,
    NewSubContractorsComponent,
    ServiceListComponent,
    ServiceDetailComponent,
    NewServiceComponent,
    ConfirmationDialogComponent,
    AlertMensajeComponent,
    ModificacionAlertComponent,
    PanelItemComponent,
    AppPanelItemProductComponent,
    AppPanelItemServiceComponent,
    AppPanelItemBillComponent,
    AppPanelItemSubcontractorsComponent,
    NewPaimentComponent,
    PaymentListComponent,
    SpinnerComponent,
    SpinnerOverlayComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    // BrowserModule,
    // BrowserAnimationsModule
  ],
  exports: [
    SendMailComponent,
    NewClienteComponent,
    ClientDetailComponent,
    NewProductComponent,
    ProductDetailComponent,
    BillListComponent,
    BillDetailProductListComponent,
    NewBillComponent,
    SubContractorsListComponent,
    SubContractorsDetailComponent,
    NewSubContractorsComponent,
    ServiceListComponent,
    ServiceDetailComponent,
    NewServiceComponent,
    ConfirmationDialogComponent,
    PaymentListComponent,
    SpinnerComponent,
    SpinnerOverlayComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ShareModule { }
