import { MatchService } from './../../services/match.service';
import { Match } from './../../models/match.model';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { TournamentService } from 'src/app/services/tournament.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  templateUrl: './match.component.html'
})
export class MatchComponent {
  id: number = 0;
  match: Match;
  goodResult = true;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public matchService: MatchService,
    public tournamentService: TournamentService, public userService: UserService,
    public loginService: LoginService) {

      this.id = activatedRoute.snapshot.params['id'];
      this.getMatch(this.id);
  }

  getMatch(id: number | string) {
    this.matchService.getMatch(id).subscribe(
      response => this.match = response,
      error => {
        if (error){
          this.router.navigate(['/error404'])
        }
      }
    );
  }

  setResult(games1, games2, games3, games4, games5, games6){
    this.matchService.resultMatch(this.match?.id, [games1, games2, games3, games4, games5, games6]).subscribe(
      _ => this.getMatch(this.id)
    );
  }

}
