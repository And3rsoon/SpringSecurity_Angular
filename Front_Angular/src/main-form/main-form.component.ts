import { Component } from '@angular/core';
import { FormGroup,FormControl,Validators,FormControlName,ReactiveFormsModule,FormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ValidacaoService } from '../app/validacao.service';
import { TokenkeyService } from '../app/app/tokenkey.service';
import { Router } from '@angular/router';
import { Input } from '@angular/core';

@Component({
  selector: 'app-main-form',
  standalone: true,
  imports:[ReactiveFormsModule,FormsModule,CommonModule],
  templateUrl: './main-form.component.html',
  styleUrl: './main-form.component.css'
})
export class MainFormComponent {
      
      token:any;
      formulario:FormGroup;
      status:boolean=false;


      constructor(private validacao:ValidacaoService,private tokenServ:TokenkeyService,private route:Router){ 
        this.formulario=new FormGroup({
            user:new FormControl('',Validators.minLength(8)),
            password: new FormControl('',Validators.minLength(8))
      });
     }
      
      onSubmit() {
        this.validacao.realizarLogin(this.formulario.get('user')?.value,this.formulario.get('password')?.value)
                              .subscribe((response:HttpResponse<any>) => {
                                if(response.status==200){
                                    this.token=response.headers.get('Authorization');
                                    this.tokenServ.setToken(this.token);
                                    this.route.navigate(['inicio']);
                                }
                                else{
                                    this.status=true;
                                }
      });
      }

}
