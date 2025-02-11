import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { DashboardData } from 'src/app/models/dashboard-data.model';
import { DashboardService } from 'src/app/services/dashboard.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-dash',
  templateUrl: './dash.component.html',
  styleUrls: ['./dash.component.css']
})
export class DashComponent implements OnInit {

  data: DashboardData;
  private present$ = new Subject<void>();
  title = 'Dashboard';

  /** Based on the screen size, switch from standard to one column per row */
  cardLayout = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return {
          columns: 1,
          miniCard: { cols: 1, rows: 1 },
          chart: { cols: 1, rows: 2 },
          table: { cols: 1, rows: 4 },
        };
      }

      return {
        columns: 4,
        miniCard: { cols: 1, rows: 1 },
        chart: { cols: 2, rows: 2 },
        table: { cols: 4, rows: 4 },
      };
    })
  );

  constructor(private breakpointObserver: BreakpointObserver, private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.dashboardService.dashboardData$.pipe(takeUntil(this.present$)).subscribe(recovery => {
      this.data = new DashboardData();
      this.data = this.data.copyModel(recovery);
      // console.log('dash - data', this.data);
    });
  }


  fillListChart(dataWork: DashboardData): DashboardData {
    dataWork.listPaidInvoiceEstimate.push(dataWork.totalAmountPaindIntYear);
    dataWork.listPaidInvoiceEstimate.push(dataWork.totalInvoicedInTheYear);
    dataWork.listPaidInvoiceEstimate.push(dataWork.totalEstimateApprovedInYear);
    dataWork.listEstimateApruveAndCreate.push(dataWork.numberEstimatesCreate);
    dataWork.listEstimateApruveAndCreate.push(dataWork.numberEstimatesApproved);
    return dataWork;
  }

}
