import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AESEncryptDecryptService } from './aesencrypt-decrypt-service.service';


@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {

  constructor(protected http: HttpClient, private router: Router, private encryptService: AESEncryptDecryptService) { }

  // http://localhost:8090/jshandyman/config/taxes/and/price/save
  sendTaxes(body: any): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(environment.serverUrl + 'config/taxes/and/price/save', body, { headers });
  }

// http://localhost:8090/jshandyman/config/emaildataconfig/save
sendEmailconfig(body: any): Observable<any> {
  const headers = new HttpHeaders();
  headers.append('Content-Type', 'application/json');
  return this.http.post(environment.serverUrl + 'config/emaildataconfig/save', body, { headers });
}

saveParameter(body: any): Observable<any> {
  const headers = new HttpHeaders();
  headers.append('Content-Type', 'application/json');
  return this.http.post(environment.serverUrl + 'config/parameters/save/automatic', body, { headers });
}


setAndSaveTemplate(body: any): Observable<any> {
  const headers = new HttpHeaders();
  headers.append('Content-Type', 'application/json');
  return this.http.post(environment.serverUrl + 'config/Template/saves', body, { headers });
}


 // http://localhost:8090/jshandyman/config/Template/set
 setTemplate(): Observable<any> {
  const opciones = {
    headers: new HttpHeaders(),
  };
  return this.http.get(environment.serverUrl + `config/Template/set`, opciones);
}


// http://localhost:8090/jshandyman/config/configuration/save
sendConfigurationData(body: any): Observable<any> {
  const headers = new HttpHeaders();
  headers.append('Content-Type', 'application/json');
  return this.http.post(environment.serverUrl + 'config/configuration/save/New', body, { headers });
}

encrypt(data): string {
  return this.encryptService.encripted(data);
}


decrypt(dataEcrypted): string {
  return this.encryptService.decrypted(dataEcrypted);
}



}
