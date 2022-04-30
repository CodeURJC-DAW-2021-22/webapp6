import { User } from './../models/user.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs';
import { Match } from '../models/match.model';
import { Router } from '@angular/router';

const BASE_URL = '/api/users/';

@Injectable({
  providedIn: 'root'
})
export class UserService {


constructor(private http: HttpClient, private router: Router) { }

  //getuser by id
  getUser(id: number | string) {
    return this.http.get(BASE_URL + id).pipe(map(
      response => response as User
    ))
  }

  registerUser(User: User) {
      return this.http.post(BASE_URL, User,{ withCredentials: true }).subscribe(
        _ => this.router.navigate(['login'])
      );
  }

  getUserImage(id: number, aux: number) {
    return BASE_URL + "image/" + id + "?" + aux
  }

  setUserImage(formData: FormData) {
    return this.http.post(BASE_URL + 'image', formData).pipe(map(
      response => response,
      _error => console.error('Unexpected Error on setUserImage')
    ))
  }

  deleteUserImage(){
    return this.http.delete(BASE_URL + 'image').pipe(map(
      response => response,
      error => errorIgnore(error, 400, "deleteUserImage")
    ))
  }

  deleteUser(id: number | string) {
    return this.http.put(BASE_URL + id, { withCredentials: true } ).pipe(map(
      response => response,
      error => errorIgnore(error, 400, "deleteUser")
    ))
  }

  updateUser(user: User) {
    return this.http.put(BASE_URL , user,{ withCredentials: true }).pipe(map(
      response => response,
      error => errorIgnore(error, 400, "updateUser")
    ));
  }

  getAllUsers(page: number | string) {
    return this.http.get(BASE_URL + "?page=" + page, { withCredentials: true }).pipe(map(
      response => getInfoPageable(response),
      error => errorIgnore(error, 403, "getAllUsers")
    ));
  }

  getUserPairs(page: number | string) {
    return this.http.get(BASE_URL + "me/pairs?page=" + page, { withCredentials: true }).pipe(map(
      response => getInfoPageable(response),
      error => errorIgnore(error, 403, "getUserPairs")
    ));
  }

  getUserMatches() {
    return this.http.get(BASE_URL + 'me/matches', { withCredentials: true }).pipe(map(
      response => response as Match[],
      error => errorIgnore(error, 403, "getUserMatches")
    ));
  }

  getUserTournaments(page: number | string) {
    return this.http.get(BASE_URL + "me/tournaments?page=" + page, { withCredentials: true }).pipe(map(
      response => getInfoPageable(response),
      error => errorIgnore(error, 403, "getUserTournaments")
    ));
  }
}


function getInfoPageable(response: any): [any[], boolean] {
  if (response.content != undefined) {
    return [response.content,!response.last]
  } else {
    return [[],false]
  }
}

function errorIgnore(error: any, errorNum: number, funcName: string) {
  if (error.status != errorNum) {
    console.error('Unexpected Error on' + funcName)
  }
}

