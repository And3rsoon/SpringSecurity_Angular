import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})


export class ValidacaoService {

  body?:object;
  resposta:any;
  constructor(private http: HttpClient) { }


  validar(token:any): Observable<any> {
      console.log(token);
      let httpheader=new HttpHeaders()
        .set('Authorization',`${token}`)
        .set('Content-Type','application/json');
      const options = { headers: httpheader };
      return this.http.get<any>('http://localhost:8000/user/autenticar',options);
  }

  realizarLogin(nome:String,password:String): Observable<any> {
    return this.http.post<any>('http://localhost:8000/user/login',{username:nome,password:password}, { observe: 'response' });
  }

  cadastro(nome:String,email:String,user:String,password:String):Observable<any>{
    this.body={id:1,nome:nome,email:email,username:user,password:password};
    return this.http.post<any>('http://localhost:8000/user/cadastrar',this.body, { observe: 'response' });
  }
}
