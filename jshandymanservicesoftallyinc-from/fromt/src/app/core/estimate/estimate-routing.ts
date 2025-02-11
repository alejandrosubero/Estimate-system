import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from 'src/app/services/auth-guard-service.service';
import { EditEstimateComponent } from './edit-estimate/edit-estimate.component';
import { EstimateComponent } from './estimate/estimate.component';
import { ViewEstimateComponent } from './view-estimate/view-estimate.component';


const routes: Routes = [
    {
        path: 'list', component: EstimateComponent,
        children: [
            { path: 'view', component: ViewEstimateComponent, canActivate: [AuthGuardService] },
        ], canActivate: [AuthGuardService]
    },
    { path: 'edid-and-detail', component: EditEstimateComponent, canActivate: [AuthGuardService] },
    { path: 'new', component: EditEstimateComponent, canActivate: [AuthGuardService] }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EstimateRoutingModule { }
