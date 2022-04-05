import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Tournament } from '../models/tournament.model';

const BASE_URL = '/api/tournaments/';

@Injectable({
  providedIn: 'root'
})
export class TournamentService {

constructor() { }

}
