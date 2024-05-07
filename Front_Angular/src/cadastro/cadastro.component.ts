import { Component } from '@angular/core';
import { FormGroup,FormControl,Validators,FormControlName,ReactiveFormsModule,FormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ValidacaoService } from '../app/validacao.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports:[ReactiveFormsModule,FormsModule,CommonModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})


export class CadastroComponent {
  formulario:FormGroup;

  constructor(private validacao:ValidacaoService){ 
    this.formulario=new FormGroup({
        nome:new FormControl('',[Validators.minLength(8),Validators.required]),
        email:new FormControl('',[Validators.email,Validators.required]),
        user:new FormControl('',[Validators.minLength(8),Validators.required,Validators.maxLength(8)]),
        password: new FormControl('',[Validators.minLength(8),Validators.required,Validators.maxLength(8)])
  });
 }
  
  onSubmit() {
      this.validacao.cadastro(this.formulario.get('nome')?.value,this.formulario.get('email')?.value,this.formulario.get('user')?.value,this.formulario.get('password')?.value)
                              .subscribe((response:HttpResponse<any>) => {console.log(response.status);
      });

  }
}
