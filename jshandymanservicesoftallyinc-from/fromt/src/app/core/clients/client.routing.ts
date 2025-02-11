import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from 'src/app/services/auth-guard-service.service';
import { ClientsComponent } from './client-list/clients.component';



const routes: Routes = [
  { path: 'client-list', component:  ClientsComponent, canActivate : [AuthGuardService]  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
