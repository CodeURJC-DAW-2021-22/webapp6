import {Component} from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { MatchService } from 'src/app/services/match.service';


@Component({
    selector: 'header',
    templateUrl: './header.component.html'
})
export class HeaderComponent {
  constructor(public loginService: LoginService, public matchService: MatchService) { }

}
