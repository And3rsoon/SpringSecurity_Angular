import { Injectable, inject } from "@angular/core";
import { Router } from "@angular/router";
export {PermissionsService}


@Injectable(
    {providedIn: 'root'}
)
class PermissionsService {
    router=inject(Router);
  canActivate(currentUser: any, userId: string): boolean {
    //this.router.navigate(['home']);
    return true;
  }
  canMatch(currentUser: any): boolean {
    return true;
  }
}