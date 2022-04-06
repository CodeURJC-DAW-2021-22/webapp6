import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable, throwError } from 'rxjs';

import { Tournament } from '../models/tournament.model';
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
}
