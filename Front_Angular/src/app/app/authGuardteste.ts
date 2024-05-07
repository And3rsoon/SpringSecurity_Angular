import { inject } from "@angular/core";
import { Router } from "@angular/router";
import { ValidacaoService } from "../validacao.service";
import { TokenkeyService } from "./tokenkey.service";


export const authGuardteste=()=>{
        const router=inject(Router);
        const autService=inject(ValidacaoService);
        const token=inject(TokenkeyService);
        
        
        if(token!=null){
                console.log(token.getToken());
                autService.validar(token.getToken()).subscribe(
                    resultado => {
                      return true;
                    },
                    erro => {
                      if(erro.status == 401) {
                        console.log(token.getToken());
                        router.navigate(['home']);
                      }
                    }
                  );
        }
        else{
            console.log("token nulo");
            router.navigate(['home']);

        }     
}