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

  constructor(private http: HttpClient, private router: Router) {
  }

  getTournament(id: number | string) {
    return this.http.get(BASE_URL + id, { withCredentials: true }) as Observable<Tournament>;
    // return this.http.get(BASE_URL + id, { withCredentials: true }).pipe(catchError(error => this.router.navigate(['/error_404'])));
  }

  getTournaments(page: number | string) {
    return this.http.get(BASE_URL + "?page=" + page, { withCredentials: true }) as Observable<any>;
  }

  creteTournament(Tournament: Tournament) {

    if (!Tournament.id) {
      return this.http.post(BASE_URL, Tournament)
        .pipe(
          catchError(error => this.handleError(error))
        );
    } else {
      return this.http.put(BASE_URL + Tournament.id, Tournament).pipe(
        catchError(error => this.handleError(error))
      );
    }
  }

  private handleError(error: any) {
    console.log("ERROR:");
    console.error(error);
    return throwError("Server error (" + error.status + "): " + error.text())
  }
}
