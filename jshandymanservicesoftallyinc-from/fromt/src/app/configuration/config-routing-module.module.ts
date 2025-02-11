import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from 'src/app/services/auth-guard-service.service';
import { ConfigurationMenuComponent } from './configuration-menu/configuration-menu.component';
import { DataConfigurationComponent } from './data-configuration/data-configuration.component';
import { MailConfigurationComponent } from './mail-configuration/mail-configuration.component';
import { TaxesConfigurationComponent } from './taxes-configuration/taxes-configuration.component';
import { UserConfigComponent } from './user-config/user-config.component';


const routes: Routes = [
  // {
  //   path: 'menu', component: ConfigurationMenuComponent,
  //     children: [
  //         { path: 'user', component: UserConfigComponent, canActivate : [AuthGuardService]},
  //   ]
  //   , canActivate : [AuthGuardService]
  // },
  { path: 'menu', component: ConfigurationMenuComponent, canActivate: [AuthGuardService] },
  { path: 'user', component: UserConfigComponent, canActivate: [AuthGuardService] },
  { path: 'taxes', component: TaxesConfigurationComponent, canActivate: [AuthGuardService] },
  { path: 'data', component: DataConfigurationComponent, canActivate: [AuthGuardService] },
  { path: 'mail', component: MailConfigurationComponent, canActivate: [AuthGuardService] },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConfigRoutingModuleModule { }
