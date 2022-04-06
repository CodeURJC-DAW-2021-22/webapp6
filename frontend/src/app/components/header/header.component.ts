import {Component} from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';


@Component({
    selector: 'header',
    templateUrl: './header.component.html'
})
export class HeaderComponent {
  constructor(public loginService: LoginService, public userService: UserService) { }

}
