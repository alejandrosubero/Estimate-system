import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuardService } from '../services/auth-guard-service.service';
import { DashComponent } from './dash/dash.component';
import { HomeComponent } from './home/home.component';
import { MenuHomeComponent } from './menu-home/menu-home.component';
import { SearchComponent } from './search/search.component';


const routes: Routes = [
  {
    path: 'menu', component: MenuHomeComponent,
    // path: 'menu', component: DashComponent,
    children: [
      { path: 'home', component: HomeComponent, canActivate: [AuthGuardService] },
      {
        path: 'estimate',
        loadChildren: () => import('../core/estimate/estimate.module').then(mod => mod.EstimateModule)
      },
      {
        path: 'invoice',
        loadChildren: () => import('../core/invoice/invoice.module').then(mod => mod.InvoiceModule)
      },
      {
        path: 'client',
        loadChildren: () => import('../core/clients/client.module').then(mod => mod.ClientModule)
      },
      {
        path: 'configuration',
        loadChildren: () => import('../configuration/config.module').then(mod => mod.ConfigModule)
      },
      { path: 'search', component: SearchComponent, canActivate: [AuthGuardService] },
      { path: 'dashboard', component: DashComponent, canActivate: [AuthGuardService] }
    ]
    , canActivate: [AuthGuardService]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }
