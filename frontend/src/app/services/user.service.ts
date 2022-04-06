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
        catchError(error => this.handleError(error))
      );
  } else {
    return this.HttpClient.put(BASE_URL + User.id, User).pipe(
      catchError(error => this.handleError(error))
    );
  }
}

setUserImage(user: User, formData: FormData) {
  return this.HttpClient.post(BASE_URL + '/image', formData)
    .pipe(
      catchError(error => this.handleError(error))
    );
}

deleteUserImage(user: User) {
  return this.HttpClient.delete(BASE_URL + user.id + '/image')
    .pipe(
      catchError(error => this.handleError(error))
    );
}

deleteUser(user: User) {
  return this.HttpClient.delete(BASE_URL + user.id).pipe(
    catchError(error => this.handleError(error))
  );
}

updateUser(user: User) {
  return this.HttpClient.put(BASE_URL + user.id, user).pipe(
    catchError(error => this.handleError(error))
  );
}

private handleError(error: any) {
  console.log("ERROR:");
  console.error(error);
  return throwError("Server error (" + error.status + "): " + error.text())
}


}
