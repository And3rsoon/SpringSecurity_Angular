import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CadastroComponent } from '../../cadastro/cadastro.component';
import { HomeComponent } from '../../home/home.component';
import { MainFormComponent } from '../../main-form/main-form.component';
import { canActivateTeam } from './authGuard';
import { authGuardteste } from './authGuardteste';
import { authenticatedGuard } from './authenticatedGuard';

export const routes: Routes = [
    {path:'',redirectTo:'home',pathMatch:'full'},
    {path:'home',component:MainFormComponent,canActivate:[authenticatedGuard]},
    {path:'inicio',component:HomeComponent,canActivate:[authGuardteste]},
    {path:'cadastro',component:CadastroComponent}
];