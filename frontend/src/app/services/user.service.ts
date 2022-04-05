import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { User } from '../models/user.model';

const BASE_URL = '/api/users/';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

constructor() { }

}
