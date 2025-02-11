import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class AESEncryptDecryptService {

  constructor() { }

  secretKey = 'YourSecretKeyForEncryption&Descryption';

  // Cifrado
  encripted(data: any) {
  

    if (data != null && data !== undefined && data !== '') {
      const key = CryptoJS.enc.Latin1.parse('abcdef0123456789');
      const iv = CryptoJS.enc.Latin1.parse('0123456789abcdef');
      const encrypted = CryptoJS.AES.encrypt(data, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.ZeroPadding
      });

      // console.log('Cifrado: ', encrypted.toString());
      return encrypted.toString();
    }
    return data;
  }


  // Descifrar
  decrypted(dataEncrypted: any) {
    const key = CryptoJS.enc.Latin1.parse('abcdef0123456789');
    const iv = CryptoJS.enc.Latin1.parse('0123456789abcdef');
    const decrypted = CryptoJS.AES.decrypt(dataEncrypted, key, {
      iv: iv,
      padding: CryptoJS.pad.ZeroPadding
    });
    // console.log('Descifrar:', decrypted.toString(CryptoJS.enc.Utf8));
    return decrypted.toString(CryptoJS.enc.Utf8);
  }



  encrypt(value: string): string {
    return CryptoJS.AES.encrypt(value, this.secretKey.trim()).toString();
  }

  decrypt(textToDecrypt: string) {
    return CryptoJS.AES.decrypt(textToDecrypt, this.secretKey.trim()).toString(CryptoJS.enc.Utf8);
  }


}




