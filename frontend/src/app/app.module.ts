import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { routing } from './app.routing';

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
import { DoughnutChart} from './components/doughnutchart/doughnutchart.component';


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
    DoughnutChart,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    DoughnutChart,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

}
