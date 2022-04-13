import { User } from './../models/user.model';
import { Match } from './../models/match.model';
import { LoginService } from './login.service';
import { Team } from './../models/team.model';
import { Tournament } from './../models/tournament.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';

const BASE_URL = '/api/tournaments/';

@Injectable({
  providedIn: 'root'
})
export class TournamentService {

  constructor(private http: HttpClient, private router: Router, private loginService :LoginService) {
  }

  getTournament(id: number | string) {
    return this.http.get(BASE_URL + id, { withCredentials: true }) as Observable<Tournament>;
    // return this.http.get(BASE_URL + id, { withCredentials: true }).pipe(catchError(error => this.router.navigate(['/error_404'])));
  }

  getTournaments(page: number | string) {
    return this.http.get(BASE_URL + "?page=" + page, { withCredentials: true }) as Observable<any>;
  }

  getTournamentTeams(id: number | string) {
    return this.http.get(BASE_URL + id + "/teams", { withCredentials: true }) as Observable<Team[]>;
  }

  getTournamentRound(id: number | string, round: number | string) {
    return this.http.get(BASE_URL + id + "/matches?round=" + round, { withCredentials: true }) as Observable<Match[]>;
  }

  createTournament(Tournament: Tournament) {

      // let To : Tournament;
      // To={owner:"owner",tournamentName:"tournamentName",numParticipants:1,numSignedUp:0,rounds:0,about:"about",ruleset:"ruleset",
      // location:"location", inscriptionDate:"2022-12-16T16:00",startDate:"2022-12-16T16:00"}
      return this.http.post(BASE_URL, Tournament, { withCredentials: true }).subscribe((resp: any) => {
        console.log("Creation Tournament: Successfully");
        this.router.navigate(['']);
      });

  }

  updateTournament(tournament: Tournament) {
    return this.http.put(BASE_URL + tournament.id, tournament).pipe(
			catchError(error => this.handleError(error))
		);
  }

  inscription(id: number | string, user2: User) {
    return this.http.put(BASE_URL + id + "/teams", user2);
  }

  private handleError(error: any) {
    console.log("ERROR:");
    console.error(error);
    return throwError("Server error (" + error.status + "): " + error.text())
  }

  getTournamentImageAPI(id: number | undefined) {
    return this.http.get(BASE_URL + id + "/image", { withCredentials: true }) as Observable<Blob>;
  }

  // getTournamentImage(id: number | undefined) {
  //   this.getTournamentImageAPI(id).subscribe(
  //     tournamentImage => {
  //       return tournamentImage;
  //     },
  //     error => {
  //       console.error('Unexpected Error on getUserTournaments')
  //     }
  //   )
  // }
}
