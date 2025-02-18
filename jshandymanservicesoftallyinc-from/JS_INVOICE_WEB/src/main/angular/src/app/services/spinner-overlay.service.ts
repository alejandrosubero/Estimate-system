import { Overlay, OverlayRef } from '@angular/cdk/overlay';
import { ComponentPortal } from '@angular/cdk/portal';
import { Injectable } from '@angular/core';
import { SpinnerOverlayComponent } from '../share/spinner-overlay/spinner-overlay.component';

@Injectable({
  providedIn: 'root'
})
export class SpinnerOverlayService {

  private overlayRef: OverlayRef = null;
  public spinnerActive: boolean = true;

  constructor(private overlay: Overlay) { }

  public show(message = ''): void {
    // Returns an OverlayRef (which is a PortalHost)
      if (!this.overlayRef) {
        this.overlayRef = this.overlay.create();
      }
      // Create ComponentPortal that can be attached to a PortalHost
      const spinnerOverlayPortal = new ComponentPortal(SpinnerOverlayComponent);
      const component = this.overlayRef.attach(spinnerOverlayPortal); // Attach ComponentPortal to PortalHost
  }

  public hide(): void {
    if (!!this.overlayRef) {
      this.overlayRef.detach();
      this.overlayRef = null;
    }
  }
}
