import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TooltipPosition } from '@angular/material/tooltip';
import { EntityRespone } from 'src/app/models/entity-respone.model';
import { AlertConfigurationService } from 'src/app/services/alert-configuration.service';
import { DashboardService } from 'src/app/services/dashboard.service';
import { InvoiceAlertComponent } from '../invoice/invoice-list-alert/invoice-alert.component';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  positionOptions: TooltipPosition[] = ['below', 'above', 'left', 'right'];
  position = new FormControl(this.positionOptions[0]);
  buttonView = false;


  constructor(
    private alertConfigurationService: AlertConfigurationService, 
    private dashboardService: DashboardService,
    public snackBar: MatSnackBar, ) {}

  ngOnInit(): void {
    this.alertConfigurationService.checkConfiguration();
    this.dashboardService.getDashboardData().subscribe((x: EntityRespone) => {
      this.dashboardService.updatedDashboardData(x.entidades[0]);
    }, (error: any) => {
      this.snackBar.open('FAILL THE RESPONSE OFF SERVER', 'Cerrar');
    });
  }

 

}

