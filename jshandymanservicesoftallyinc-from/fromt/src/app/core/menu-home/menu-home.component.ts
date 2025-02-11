import { AfterViewInit, ChangeDetectorRef, Component, OnChanges, OnInit, SimpleChanges, ViewEncapsulation } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TooltipPosition } from '@angular/material/tooltip';
// import { Router } from '@angular/router';
import { Router, Event, NavigationStart, NavigationEnd, NavigationError } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { LogingResponse } from 'src/app/models/loging-response.model';
import { Work } from 'src/app/models/work.model';
import { WorkListTabletPojo } from 'src/app/models/workListTabletPojo.model';
import { CoreService } from 'src/app/services/core.service';
import { DashboardService } from 'src/app/services/dashboard.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { InvoiceAlertComponent } from '../invoice/invoice-list-alert/invoice-alert.component';


@Component({
  selector: 'app-menu-home',
  templateUrl: './menu-home.component.html',
  styleUrls: ['./menu-home.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MenuHomeComponent implements OnInit, OnChanges {

  public iscollapse = true;
  public iscollapse2 = true;
  photo;
  seccion: LogingResponse;
  search = '';
  imgsrc: any;
  public profileImagin = '../../../assets/images/user-profile1.png';
  numberAlerts: number = 0;
  colorAlert = 'primary';
  positionOptions: TooltipPosition[] = ['below', 'above', 'left', 'right'];
  position = new FormControl(this.positionOptions[0]);
  private present$ = new Subject<void>();
  searchInput = '';
  currentRoute: string;
  routeMenu = '/jshandy-man-services/menu';

  constructor(
    private router: Router,
    private coreService: CoreService,
    private sessionService: SessionStorageService,
    private dashboardService: DashboardService,
    public snackBar: MatSnackBar,
    private dialog: MatDialog, ) {

    this.currentRoute = 'Demo';

    this.router.events.subscribe((event: Event) => {
      if (event instanceof NavigationEnd) {
        this.currentRoute = event.url;
        // console.log(event);
        // console.log('this.currentRoute; ', this.currentRoute);
        if (this.currentRoute == this.routeMenu){
          this.coreService.navigateByUrl('/jshandy-man-services/menu/home');
        }
      }
    });
  }


  ngOnChanges(changes: SimpleChanges): void {
    console.log('ngOnChanges in MenuHomeComponent...');
    this.dashboardService.alertData$.pipe(takeUntil(this.present$)).subscribe(recoveryAlert => {
      this.dateLate(recoveryAlert);
    });
  }


  ngOnInit(): void {
    this.seccion = this.sessionService.get('UserSession');
    this.setImaging(this.seccion.userImagen);
    this.coreService.navigateByUrl('/jshandy-man-services/menu/home');
    this.dashboardService.alert$.subscribe(alertList => {
      this.dateLate(alertList);
    });
  }


  scrollElemet($element): void {
    $element.scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'nearest' });
  }


  setImaging(imagen: string): void {
    const imgSrc = document.getElementById('pifileImg');
    imgSrc.setAttribute('src', imagen);
  }


  buscar(keyword: string): void {
    this.router.navigate(
      ['/jshandy-man-services/menu/search'],
      { queryParams: { id: keyword } }
    );
    this.searchInput = '';
  }


  logout(): void {
    this.coreService.logout();
  }

  onSelltings(): void {
    this.coreService.navigateByUrl('/jshandy-man-services/menu/configuration/menu');
  }


  onCreateNewEstimate(): void {
    this.coreService.navigateByUrl('/jshandy-man-services/menu/estimate/edid-and-detail');
  }

  onCreateNewWork(): void {
    this.coreService.navigateByUrl('/jshandy-man-services/menu/invoice/new');
  }

  changeColase() {
    this.iscollapse = this.iscollapse ? false : true;
    // if (this.iscollapse) {
    //   this.iscollapse = false;
    // } else {
    //   this.iscollapse = true;
    // }
  }


  dateLate(works: Array<WorkListTabletPojo>): boolean {
    let valor: boolean;
    this.numberAlerts = works.length;
    this.color(works.length);
    this.numberAlerts > 0 ? valor = false : valor = true;
    return valor;
  }

  color(worksLength: number): void {
    this.colorAlert = worksLength > 0 ? 'accent' : 'primary';
  }

  onSeeTablet(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.height = '80%';

    const dialogRef = this.dialog.open(InvoiceAlertComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(x => { });
  }

}
