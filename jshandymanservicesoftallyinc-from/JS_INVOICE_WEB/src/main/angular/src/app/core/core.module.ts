import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuHomeComponent } from './menu-home/menu-home.component';
import { MaterialModule } from '../materialModule/materialModule';
import { CoreRoutingModule } from './core-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './home/home.component';

import { SearchComponent } from './search/search.component';
import { ShareModule } from '../share/share.module';
import { AuthGuardService } from '../services/auth-guard-service.service';
import { SearchEstimateListComponent } from './search/search-estimate-list/search-estimate-list.component';
import { SearchInvoiceListComponent } from './search/search-invoice-list/search-invoice-list.component';
import { SearchServicesListComponent } from './search/search-services-list/search-services-list.component';
import { SearchSubcontractorListComponent } from './search/search-subcontractor-list/search-subcontractor-list.component';
import { DashComponent } from './dash/dash.component';
import { CardComponent } from './dash/card/card.component';
import { MiniCardComponent } from './dash/mini-card/mini-card.component';
import { ChartComponent } from './dash/charts/chart/chart.component';
import { Chart2Component } from './dash/charts/chart2/chart2.component';
import { SendDetailComponent } from './dash/send-detail/send-detail.component';
import { DetailComponent } from './dash/detail/detail.component';




@NgModule({
  declarations: [
    MenuHomeComponent,
    HomeComponent,
    SearchComponent,
    SearchEstimateListComponent,
    SearchInvoiceListComponent,
    SearchServicesListComponent,
    SearchSubcontractorListComponent,
    DashComponent,
    CardComponent,
    MiniCardComponent,
    ChartComponent,
    Chart2Component,
    SendDetailComponent,
    DetailComponent,
    // UserConfigComponent
  ],
  imports: [
    CommonModule,
    NgbModule,
    CoreRoutingModule,
    MaterialModule,
    ShareModule
  ],
  providers: [
    AuthGuardService,
  ],
})
export class CoreModule { }
