import {Component} from '@angular/core';
import { Match } from 'src/app/models/match.model';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';


@Component({
    selector: 'header',
    templateUrl: './header.component.html'
})
export class HeaderComponent {

  userMatches: Match[] = [];

  constructor(public loginService: LoginService, public userService: UserService) {
    this.getUserMatches();
  }

  getUserMatches(){
    this.userService.getUserMatches().subscribe(
      listMatches => this.userMatches = listMatches,
      error => {
        if (error.status != 403) {
            console.error('Unexpected Error on getUserMatches')
        }
      }
    )
  }

  hasUserMatches() {
    return this.userMatches.length > 0
  }

  numUserMatches() {
    return this.userMatches.length
  }

}
