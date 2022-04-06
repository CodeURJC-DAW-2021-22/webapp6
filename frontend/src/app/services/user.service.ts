import { User } from './../models/user.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

const BASE_URL = '/api/users/';

@Injectable({
  providedIn: 'root'
})
export class UserService {


constructor(private HttpClient: HttpClient) { }

//getuser by id
getUser(id: number | string): Observable<User> {
  return this.HttpClient.get(BASE_URL + id).pipe(
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

  if (!User.id) {
    return this.HttpClient.post(BASE_URL, User)
      .pipe(
        //catchError(error => this.handleError(error))
      );
  } else {
    return this.HttpClient.put(BASE_URL + User.id, User).pipe(
      //catchError(error => this.handleError(error))
    );
  }
}



}
