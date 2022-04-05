import {Component} from '@angular/core';

import { LoginService } from './services/login.service';

@Component({
    selector: 'header',
    templateUrl: './header.component.html'
})
export class HeaderComponent {
  constructor(public loginService: LoginService) { }

}
