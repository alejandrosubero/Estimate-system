import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-modificacion-alert',
  templateUrl: './modificacion-alert.component.html',
  styleUrls: ['./modificacion-alert.component.css']
})
export class ModificacionAlertComponent implements OnInit {

  title: string;
  item: string;
  mensaje = `The item will be removed from the list, which will be shown but will not be taken into account for the price calculations in the estimate or invoice.`;

  constructor(@Inject(MAT_DIALOG_DATA) public data?: any) {

    if (data.title !== undefined && data.title != null) {
      this.title = data.title;
    } else {
      this.title = `Item Modification Alert`;
    }

    if (data.msg !== undefined && data.msg != null){
      this.item = data.msg;
    }else{
      this.item = `Item`;
    }
  }

  ngOnInit(): void {
  }

}


