import { LoginService } from 'src/app/services/login.service';
import { Tournament } from './../../models/tournament.model';
import { Component } from '@angular/core';
import { TournamentService } from 'src/app/services/tournament.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  templateUrl: './main.component.html'
})
export class MainComponent {

  userTournaments: Tournament[] = [];
  tournaments: Tournament[] = [];
  hasMoreTournaments: boolean = true;
  hasMoreUserTournaments: boolean = true;
  page: number = -1;
  pageUser: number = -1;

  constructor(public loginService: LoginService, public tournamentService: TournamentService, public userService: UserService) {
    this.getTournaments();
    this.getUserTournaments();
  }

  getTournaments() {
    this.page = this.page + 1;
    this.tournamentService.getTournaments(this.page).subscribe(
      listTournaments => {
        if (listTournaments.content != undefined) {
          this.tournaments = this.tournaments.concat(listTournaments.content);
          this.hasMoreTournaments = !listTournaments.last;
        } else {
          this.hasMoreTournaments = false;
        }
      },
      error => {
        console.error('Unexpected Error on getTournaments')
      }
    )
  }

  getUserTournaments() {
    this.pageUser = this.pageUser + 1;
    this.userService.getUserTournaments(this.pageUser).subscribe(
      listTournaments => {
        if (listTournaments.content != undefined) {
          this.userTournaments = this.userTournaments.concat(listTournaments.content);
          this.hasMoreUserTournaments = !listTournaments.last;
        } else {
          this.hasMoreUserTournaments = false;
        }
      },
      error => {
        if (error.status != 403) {
          console.error('Unexpected Error on getUserTournaments')
        }
      }
    )
  }
}
