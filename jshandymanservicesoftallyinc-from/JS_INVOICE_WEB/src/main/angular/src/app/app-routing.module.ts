import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogingComponent } from './base/loging/loging.component';
import { NewUserComponent } from './base/new-user/new-user.component';
import { RecoveryUserDataComponent } from './base/recovery-user-data/recovery-user-data.component';
import { HomeComponent } from './core/home/home.component';
import { AuthGuardService } from './services/auth-guard-service.service';


const routes: Routes = [
  { path: '', component: LogingComponent },
  { path: 'login', component: LogingComponent},
  { path: 'newUser', component: NewUserComponent},
  { path: 'recoveryUser', component: RecoveryUserDataComponent},
  {
    path: 'jshandy-man-services',
    loadChildren: () => import('./core/core.module').then(mod => mod.CoreModule)
  },
  {path: '**', redirectTo: 'jshandy-man-services/menu/home'}
  // { path: '**', component: HomeComponent,  canActivate: [AuthGuardService]},
  // {
  //   path: 'jshandy-man-services-configuration',
  //   loadChildren: () => import('./configuration/config.module').then(mod => mod.ConfigModule)
  // },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
