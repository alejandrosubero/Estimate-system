import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-alert-mensaje',
  templateUrl: './alert-mensaje.component.html',
  styleUrls: ['./alert-mensaje.component.css']
})
export class AlertMensajeComponent implements OnInit {

  title: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data?: any) {

    if (data.title !== undefined && data.title != null) {
      this.title = data.title;
    } else {
      this.title = `Alert of item`;
    }

  }

  ngOnInit(): void {
  }

}
