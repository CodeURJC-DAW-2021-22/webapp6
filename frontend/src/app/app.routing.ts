import { UserProfile } from './components/user/userProfile.component';
import { RegisterComponent } from './components/register/register.component';
import { HeaderComponent } from './components/header/header.component';

import { RouterModule } from '@angular/router';
import { MainComponent } from './components/main/main/main.component';
import { LoginComponent } from './components/login/login.component';
import {CreateTournament} from './components/tournament/createTournament.component';

const appRoutes = [
  { path: '', component: MainComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'create_tournament', component: CreateTournament },
  { path: 'user_profile', component: UserProfile }
  // { path: '', redirectTo: '', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);
