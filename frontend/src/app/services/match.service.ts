import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs';

import { Match } from '../models/match.model';
import { UserService } from './user.service';

const BASE_URL = '/api/matches/';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private http: HttpClient, public userService: UserService) {
  }

  // THIS FUNCTIONS MAY NO WORK, TEST THEM WHEN DOING THE PAGES OF MATCH

  getMatch(id: number | string) {
    return this.http.get(BASE_URL + id, { withCredentials: true }).pipe(map(
      response => response as Match,
      error => {
        if (error.status != 404) {
          console.error('Unexpected Error on getMatch')
          return false
        } else {
          return true
        }
      }
    ))
  }

  resultMatch(id: number | string, result: number[]) {
    return this.http.put(BASE_URL + id + '/result', {result: result}, { withCredentials: true });
    //We can return the match thta has been updated. Not done because not used (yet)
  }

}
