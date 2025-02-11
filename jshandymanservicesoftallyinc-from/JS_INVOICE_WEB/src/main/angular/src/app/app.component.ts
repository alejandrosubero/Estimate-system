import { Component, HostListener, OnInit } from '@angular/core';
import { LogingResponse } from './models/loging-response.model';
import { CoreService } from './services/core.service';
import { SessionStorageServiceService } from './services/session-storage-service.service';
import { SessionStorageService } from './services/session-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'JSHANDYMANSERVICESOFTALLYINCF';

  constructor() {}

  ngOnInit(): void {}

}
