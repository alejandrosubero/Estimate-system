import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';



// services
import { CoreService } from './services/core.service';
import { AlertStatusService } from './services/alert-status.service';
import { SpinnerOverlayService } from './services/spinner-overlay.service';
import { AuthGuardService } from './services/auth-guard-service.service';
import { SessionStorageService } from './services/session-storage.service';

// componet
import { AppComponent } from './app.component';
import { LogingComponent } from './base/loging/loging.component';
import { NewUserComponent } from './base/new-user/new-user.component';
import { NavigationComponent } from './base/navigation/navigation.component';
import { AddressFormComponent } from './base/address-form/address-form.component';
import { AuthInterceptorService } from './services/auth-interceptor.service';

// entryComponents
import { NewClienteComponent } from './share/client/new-cliente/new-cliente.component';
import { LogingRecuperarComponent } from './base/loging-recuperar/loging-recuperar.component';
import { RecoveryUserDataComponent } from './base/recovery-user-data/recovery-user-data.component';
import { SpinnerOverlayComponent } from './share/spinner-overlay/spinner-overlay.component';


// module
import { MaterialModule } from './materialModule/materialModule';
import {PortalModule} from '@angular/cdk/portal';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { ɵROUTER_PROVIDERS } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AddressFormComponent,
    LogingComponent,
    NewUserComponent,
    LogingRecuperarComponent,
    RecoveryUserDataComponent,
  
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialModule,
    PortalModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
  ],
  entryComponents: [
    NewClienteComponent,
    SpinnerOverlayComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    // Location , {provide: LocationStrategy, useClass: HashLocationStrategy},
    AuthGuardService,
    SessionStorageService,
    CoreService,
    AlertStatusService,
    SpinnerOverlayService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }


// export class HashLocationComponent {
//   location: Location;
//   constructor(location: Location) {
//     this.location = location;
//   }
// }
