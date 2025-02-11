import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

import { Chart } from 'chart.js';
import { CoreService } from 'src/app/services/core.service';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit, OnChanges {

  @Input() value: Array<number>;

  listLabel = ['Total Amount Paid in year', 'total Invoiced in Year', 'Total Estimate Approved In Year'];
  listData = [0, 0, 0];
  labelTitle = 'Total';
  myChart: Chart;

  constructor(private coreservice: CoreService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.value !== undefined && this.value != null && this.myChart !== undefined) {
      this.myChart.destroy();
      if (this.value !== undefined && this.value != null && this.value.length > 0) {
        this.listData = this.value;
        this.buildChart(this.listLabel, this.listData, this.labelTitle);
      }
    }
  }

  ngOnInit(): void {
    this.buildChart(this.listLabel, this.listData, this.labelTitle);
  }


  buildChart(listLabel: string[], listaDtaNumber: number[], labelTitle: string): void {
    this.myChart = new Chart("myChart", this.congfBuilder(listLabel, listaDtaNumber, labelTitle));
  }

  congfBuilder(listLabel: string[], listaDtaNumber: number[], labelTitle: string): any {

    let listaDtaNumber2: number[] = [];

    listaDtaNumber.forEach(n => {
      listaDtaNumber2.push(this.coreservice.round(n));
    });


    let confg = {
      type: 'bar',
      data: {
        labels: listLabel,
        datasets: [{
          label: labelTitle,
          data: listaDtaNumber2,
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
          ],
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    }
    return confg;
  }

  addData(chart, label, data): void {
    // chart.data.labels = label;
    chart.data.datasets.forEach((dataset) => {
      dataset.data = data;
    });
    chart.update();
  }

  removeData(chart): void {
    chart.data.datasets.forEach((dataset) => {
      dataset.data.pop();
    });
    chart.update();
  }

}
