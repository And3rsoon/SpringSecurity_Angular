import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class tokenService {
  private token: string | null = null;

  constructor() { }

  setToken(token: string) {
    this.token = token;
  }

  getToken(): string | null {
    return this.token;
  }

  isLoggedIn(): boolean {
    return !!this.token;
  }
}