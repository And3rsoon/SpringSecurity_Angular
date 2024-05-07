import { CanActivateFn } from "@angular/router";
import { ActivatedRouteSnapshot } from "@angular/router";
import { RouterStateSnapshot } from "@angular/router";
import { inject } from "@angular/core";
import { PermissionsService } from "../../services/permissaoService";

export const canActivateTeam: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ) => {
    return inject(PermissionsService).canActivate("usuario", route.params['home']);
  };