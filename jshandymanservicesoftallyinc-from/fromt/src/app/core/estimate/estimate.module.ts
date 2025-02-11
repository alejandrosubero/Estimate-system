import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EstimateComponent } from './estimate/estimate.component';
import { MaterialModule } from 'src/app/materialModule/materialModule';
import { ShareModule } from 'src/app/share/share.module';
import { EditEstimateComponent } from './edit-estimate/edit-estimate.component';
import { ViewEstimateComponent } from './view-estimate/view-estimate.component';
import { EstimateRoutingModule } from './estimate-routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthGuardService } from 'src/app/services/auth-guard-service.service';



@NgModule({
  declarations: [
    EstimateComponent,
    EditEstimateComponent,
    ViewEstimateComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ShareModule,
    NgbModule,
    EstimateRoutingModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    AuthGuardService,
  ],
})
export class EstimateModule { }
