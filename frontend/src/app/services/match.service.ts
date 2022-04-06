import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Match } from '../models/match.model';
import { UserService } from './user.service';
import { Router } from '@angular/router';

const BASE_URL = '/api/matches/';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private http: HttpClient, public userService: UserService, private router: Router) {
  }

  // THIS FUNCTIONS MAY NO WORK, TEST THEM WHEN DOING THE PAGES OF MATCH

  getMatch(id: number | string) {
    return this.http.get(BASE_URL + id, { withCredentials: true }).pipe(error => throwError('Match Not Found')) as Observable<Match>;
    // return this.http.get(BASE_URL + id, { withCredentials: true }).pipe(catchError(error => this.router.navigate(['/error_404'])));
  }

  resultMatch(id: number | string, result: number[]) {
    this.http.put(BASE_URL + id + '/result', { result: result}, { withCredentials: true }).pipe(catchError(error => throwError('Not Found')));
  }

}
