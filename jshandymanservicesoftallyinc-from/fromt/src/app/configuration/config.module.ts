import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreRoutingModule } from 'src/app/core/core-routing.module';
import { MaterialModule } from 'src/app/materialModule/materialModule';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ShareModule } from 'src/app/share/share.module';
import { AuthGuardService } from 'src/app/services/auth-guard-service.service';
import { UserConfigComponent } from 'src/app/configuration/user-config/user-config.component';
import { ConfigurationMenuComponent } from './configuration-menu/configuration-menu.component';
import { ConfigRoutingModuleModule } from './config-routing-module.module';
import { TaxesConfigurationComponent } from './taxes-configuration/taxes-configuration.component';
import { MailConfigurationComponent } from './mail-configuration/mail-configuration.component';
import { DataConfigurationComponent } from './data-configuration/data-configuration.component';
import { DataConfigurationViewComponent } from './data-configuration-view/data-configuration-view.component';


@NgModule({
  declarations: [
    UserConfigComponent,
    ConfigurationMenuComponent,
    TaxesConfigurationComponent,
    MailConfigurationComponent,
    DataConfigurationComponent,
    DataConfigurationViewComponent,
  ],
  imports: [
    CommonModule,
    NgbModule,
    ConfigRoutingModuleModule,
    MaterialModule,
    ShareModule
  ],
  providers: [
    AuthGuardService,
  ],
})
export class ConfigModule { }
