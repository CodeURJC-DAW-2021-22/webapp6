import { RouterModule } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CreateTournamentComponent } from './components/createTournament/createTournament.component';
import { UserProfileComponent } from './components/userProfile/userProfile.component';
import { TournamentComponent } from './components/tournament/tournament.component';
import { MatchComponent } from './components/match/match.component';
import { AdminComponent } from './components/admin/admin.component';
import { Error404Component } from './components/error/error_404.component';
 import {  DoughnutChartComponent } from './components/doughnutchart/doughnutchart.component';

const appRoutes = [
  { path: '', component: MainComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'create_tournament', component: CreateTournamentComponent },
  { path: 'user_profile', component: UserProfileComponent },
  { path: 'tournament/:id', component: TournamentComponent },
  { path: 'match/:id', component: MatchComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'error404', component: Error404Component },
  { path: 'doughnut', component: DoughnutChartComponent},
  // { path: '', redirectTo: '', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);
