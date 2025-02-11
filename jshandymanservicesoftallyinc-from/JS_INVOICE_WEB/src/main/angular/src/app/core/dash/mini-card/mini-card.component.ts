import { Component, Input, OnInit } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { AppSettings } from 'src/app/models/app-const.model';

@Component({
  selector: 'app-mini-card',
  templateUrl: './mini-card.component.html',
  styleUrls: ['./mini-card.component.css']
})
export class MiniCardComponent implements OnInit {

  @Input() icon: string;
  @Input() title: string;
  @Input() color: string;
  @Input() isIncrease: boolean;
  @Input() duration: string;
  @Input() percentValue: number;
  @Input() isCurrency: boolean;
  @Input() value: number;
  @Input() imagName: number;
  
  currencyIcon = AppSettings.CURRENCYICON;

  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIconLiteral( 'currency Icon' , sanitizer.bypassSecurityTrustHtml(AppSettings.CURRENCYICON));
    iconRegistry.addSvgIconLiteral( 'currencyB Icon' , sanitizer.bypassSecurityTrustHtml(AppSettings.CURRENCYICONB));
    iconRegistry.addSvgIconLiteral( 'currencyR Icon' , sanitizer.bypassSecurityTrustHtml(AppSettings.CURRENCYICONR));
    iconRegistry.addSvgIconLiteral( 'cuantity Icon' , sanitizer.bypassSecurityTrustHtml(AppSettings.CUANTITY));
    iconRegistry.addSvgIconLiteral( 'cuantityB Icon' , sanitizer.bypassSecurityTrustHtml(AppSettings.CUANTITYB));

    iconRegistry.addSvgIconLiteral( 'workCurse Icon' , sanitizer.bypassSecurityTrustHtml(AppSettings.WORKCURSE));
    iconRegistry.addSvgIconLiteral( 'workCancel Icon' , sanitizer.bypassSecurityTrustHtml(AppSettings.WORKCANCEL));
    iconRegistry.addSvgIconLiteral( 'workPause Icon' , sanitizer.bypassSecurityTrustHtml(AppSettings.WORKPAUSE));
    iconRegistry.addSvgIconLiteral( 'workEnd Icon' , sanitizer.bypassSecurityTrustHtml(AppSettings.WORKEND));
    iconRegistry.addSvgIconLiteral( 'workSent Icon' , sanitizer.bypassSecurityTrustHtml(AppSettings.WORKSET));

   }

  ngOnInit(): void {

  }

}
