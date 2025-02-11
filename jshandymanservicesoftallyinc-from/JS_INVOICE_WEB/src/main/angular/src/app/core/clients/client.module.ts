import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from 'src/app/materialModule/materialModule';
import { ShareModule } from 'src/app/share/share.module';
import { ClientsComponent } from './client-list/clients.component';
import { ClientRoutingModule } from './client.routing';
import { AuthGuardService } from 'src/app/services/auth-guard-service.service';




@NgModule({
  declarations: [
    ClientsComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ShareModule,
    ClientRoutingModule,
  ],
  providers: [
    AuthGuardService,
  ],
})
export class ClientModule { }
