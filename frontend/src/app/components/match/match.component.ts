import { Observable } from 'rxjs';
import { MatchService } from './../../services/match.service';
import { Match } from './../../models/match.model';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { TournamentService } from 'src/app/services/tournament.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  templateUrl: './match.component.html'
})
export class MatchComponent {
  id: number = 0;
  $match: Observable<Match> | undefined;
  goodResult = true;

  constructor(activatedRoute: ActivatedRoute, public matchService: MatchService,
    public tournamentService: TournamentService, public userService: UserService,
    public loginService: LoginService) {

      this.id = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.getMatch(this.id);
  }

  getMatch(id: number | string) {
    this.$match = this.matchService.getMatch(id);
  }

  // getNumResult(index: number){
  //   if (this.$match !== undefined)
  //   return this.$match.result.get(0)
  // }

}
