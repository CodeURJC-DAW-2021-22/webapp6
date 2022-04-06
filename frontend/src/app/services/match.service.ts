import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Match } from '../models/match.model';
import { UserService } from './user.service';

const BASE_URL = '/api/matches/';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private http: HttpClient, public userService: UserService) {
  }



}
