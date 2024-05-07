import { inject } from "@angular/core";
import { Router } from "@angular/router";
import { ValidacaoService } from "../validacao.service";
import { TokenkeyService } from "./tokenkey.service";


export const authenticatedGuard=()=>{
        const router=inject(Router);
        const autService=inject(ValidacaoService);
        const token=inject(TokenkeyService);
        
        
        if(token.isLoggedIn()){
            router.navigate(['inicio']);
            return false;
        }
        else{
            return true;
        }     
}