import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Chart } from "chart.js"

@Component({
  selector: 'app-chart2',
  templateUrl: './chart2.component.html',
  styleUrls: ['./chart2.component.css']
})
export class Chart2Component implements OnInit, OnChanges {

  @Input() value: Array<number>;

  listLabel = ['Create', 'Approved'];
  listData = [0, 0];
  labelTitle = 'Estimates';
  myChart: Chart;

  constructor() { }


  ngOnInit(): void {
    this.buildChart(this.listLabel, this.listData, this.labelTitle);
  }


  ngOnChanges(changes: SimpleChanges): void {
    if (this.value !== undefined && this.value != null && this.myChart !== undefined) {
      this.myChart.destroy();
      if (this.value !== undefined && this.value != null && this.value.length > 0) {
        this.listData = this.value;
        this.buildChart(this.listLabel, this.listData, this.labelTitle);
      }

    }
  }


  buildChart(listLabel: string[], listaDtaNumber: number[], labelTitle: string): void {
    this.myChart = new Chart("myChart2", this.congfBuilder(listLabel, listaDtaNumber, labelTitle));
  }


  congfBuilder(listLabel: string[], listaDtaNumber: number[], labelTitle: string): any {
    let confg = {
      type: 'bar',
      data: {
        labels: listLabel,
        datasets: [{
          label: labelTitle,
          data: listaDtaNumber,
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)'
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


  addData(chart, label, data) {
    chart.data.datasets.forEach((dataset) => {
      dataset.data = data;
    });
    chart.update();
  }

  removeData(chart) {
    chart.data.datasets.forEach((dataset) => {
      dataset.data.pop();
    });
    chart.update();
  }




}
