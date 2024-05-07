import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { TokenkeyService } from '../app/app/tokenkey.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule,],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  
constructor(private tokenService:TokenkeyService,private route:Router){}

  logOut(){
          this.tokenService.deleteToken();
          this.route.navigate(['home']);
  }
}
