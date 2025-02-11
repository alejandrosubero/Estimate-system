import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class SanitizerPipeService {

  constructor(private sanitizer: DomSanitizer) {}

  transform(url: string): any {
      if(!url) return null;
      return this.sanitizer.bypassSecurityTrustUrl(url);
  }

}
