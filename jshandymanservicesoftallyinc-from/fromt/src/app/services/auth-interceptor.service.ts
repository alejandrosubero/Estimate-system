
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

import { LogingResponse } from '../models/loging-response.model';
import { finalize } from 'rxjs/operators';
import { SessionStorageService } from './session-storage.service';
import { SpinnerOverlayService } from './spinner-overlay.service';


@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(
    private spinnerService: SpinnerOverlayService,
    private SessionService: SessionStorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (this.spinnerService.spinnerActive) {
      this.spinnerService.show();
    }

    let token: string = '';
    let authorization = '';
    const secciones: LogingResponse = this.SessionService.get('UserSession');
    if (secciones) {
      token = secciones.token;
      authorization = secciones.authorization;
    }

    let request = req;

    if (token != null && token !== undefined && token !== ''){
      request = req.clone({
        setHeaders: {
          'Content-Type': 'application/json',
          Accept: '*/*',
          Authorization: `${token}`
        }
      });
    }
    // return next.handle(request);
    return next.handle(request).pipe(finalize(() => {
      // this.loader.hide();
      if (this.spinnerService.spinnerActive) {
        this.spinnerService.hide();
      }
    })
    );
  }

}


