import { User } from './../models/user.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Match } from '../models/match.model';

const BASE_URL = '/api/users/';

@Injectable({
  providedIn: 'root'
})
export class UserService {


constructor(private http: HttpClient) { }

  //getuser by id
  getUser(id: number | string): Observable<User> {
    return this.http.get(BASE_URL + id).pipe(
      //catchError(error => this.handleError(error))
    ) as Observable<User>;
  }

  //get all users;
  // OJITO QUE AQUI HAY PAGEABLE
  // getAllUsers(): Observable<User[]> {
  //   return this.HttpClient.get(BASE_URL).pipe(
  //     //catchError(error => this.handleError(error))
  //   ) as Observable<User[]>;
  // }

  registerUser(User: User) {


      return this.http.post(BASE_URL, User,{ withCredentials: true }).subscribe((resp: any) => {
        console.log("Register new User: Successfully");

    });
  }

  setUserImage(user: User, formData: FormData) {
    return this.http.post(BASE_URL + 'image', formData)
      .pipe(
        catchError(error => this.handleError(error))
      );
  }

  deleteUserImage(user: User) {
    return this.http.delete(BASE_URL + 'image')
      .pipe(
        catchError(error => this.handleError(error))
      );
  }

  deleteUser(user: User) {
    return this.http.delete(BASE_URL + user.id).pipe(
      catchError(error => this.handleError(error))
    );
  }

  updateUser(user: User) {
    return this.http.put(BASE_URL + user.id, user).pipe(
      catchError(error => this.handleError(error))
    );
  }

  getAllUsers(page: number | string) {
    return this.http.get(BASE_URL + "?page=" + page, { withCredentials: true }) as Observable<any>;
  }

  getUserMatches() {
    return this.http.get(BASE_URL + 'me/matches', { withCredentials: true }) as Observable<Match[]>;
    // return this.http.get(BASE_URL + id, { withCredentials: true }).pipe(catchError(error => this.router.navigate(['/error_404'])));
  }

  getUserTournaments(page: number | string) {
    return this.http.get(BASE_URL + "me/matches?page=" + page, { withCredentials: true }) as Observable<any>;
  }

  private handleError(error: any) {
    console.log("ERROR:");
    console.error(error);
    return throwError("Server error (" + error.status + "): " + error.text())
  }
}
