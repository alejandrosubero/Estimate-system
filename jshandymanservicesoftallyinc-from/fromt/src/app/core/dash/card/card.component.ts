
import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { WorkListTabletPojo } from 'src/app/models/workListTabletPojo.model';
import { DetailComponent } from '../detail/detail.component';
import { SendDetailComponent } from '../send-detail/send-detail.component';



@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() title: string;
  @Input() showMenu: boolean;
  @Input() isCurrency: boolean;
  @Input() value: number;
  @Input() valueLista: Array<WorkListTabletPojo>;
  @Input() imagName: string;

  ListComponent = [DetailComponent];

  constructor(private dialog: MatDialog, private _snackBar: MatSnackBar,) { }

  ngOnInit(): void {
    // this.showData(0,this.isCurrency,this.value, this.imagName, this.title, this.valueLista)
  }


  // tslint:disable-next-line: max-line-length
  showData(comp?: number, currency?: boolean, worksSend?: number, nameImag?: string, titleCard?: string, listWorks?: Array<WorkListTabletPojo>): void {
    let componet: number = 0;
    componet = comp !== undefined && comp != null ? comp : 0;

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.maxWidth = '100vw';
    dialogConfig.maxHeight = '100vh';
    dialogConfig.width = '750px';
    dialogConfig.height = '85vh';
    dialogConfig.data = {
      isCurrency: currency,
      numberWorksSend: worksSend,
      imagName: nameImag,
      title: titleCard,
      dataRecive: listWorks
    };

    const dialogRef = this.dialog.open(this.ListComponent[componet], dialogConfig);
    dialogRef.afterClosed().subscribe(x => { });
  }



}
