import { HeaderComponent } from './components/header/header.component';

import { RouterModule } from '@angular/router';
import { MainComponent } from './components/main/main/main.component';
import { LoginComponent } from './components/login/login.component';

const appRoutes = [
  { path: '', component: MainComponent },
  { path: 'login', component: LoginComponent }
  // { path: '', redirectTo: '', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);
