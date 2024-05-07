import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenkeyService {

  constructor() { }

  deleteToken():void{
      localStorage.removeItem('token');

  }
  setToken(token: string) {
    localStorage.setItem("token",token);
  }

  getToken(): string | null {
    return localStorage.getItem("token");
  }

  isLoggedIn(): Boolean {
    return !!localStorage.getItem("token");
  }

}

