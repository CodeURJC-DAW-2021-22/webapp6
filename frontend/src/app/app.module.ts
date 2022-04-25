import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { routing } from './app.routing';
import {ChartModule} from 'primeng/chart';
import {TabViewModule} from 'primeng/tabview';
import {AppDemoActionsModule} from './app.demoactions.component';


import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { MainComponent } from './components/main/main.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserProfileComponent } from './components/userProfile/userProfile.component';
import { CreateTournamentComponent } from './components/createTournament/createTournament.component';
import { TournamentComponent } from './components/tournament/tournament.component';
import { MatchComponent } from './components/match/match.component';
import { AdminComponent } from './components/admin/admin.component';
import { Error404Component } from './components/error/error_404.component';
import { DoughnutChartComponent} from './components/doughnutchart/doughnutchart.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    LoginComponent,
    RegisterComponent,
    UserProfileComponent,
    CreateTournamentComponent,
    TournamentComponent,
    MatchComponent,
    AdminComponent,
    Error404Component,
    DoughnutChartComponent,


  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ChartModule,
    TabViewModule,
    AppDemoActionsModule,



    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

}
