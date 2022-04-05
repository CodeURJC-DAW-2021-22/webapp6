import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Match } from '../models/match.model';

const BASE_URL = '/api/matches/';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

constructor() { }

}
