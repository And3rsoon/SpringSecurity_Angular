import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { tokenService } from './token';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
 
  constructor(private tokenServ:tokenService){}
  title = 'landing-page';
}
