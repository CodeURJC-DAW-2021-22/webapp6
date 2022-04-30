import { User } from './../models/user.model';
import { Match } from './../models/match.model';
import { Team } from './../models/team.model';
import { Tournament } from './../models/tournament.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, throwError } from 'rxjs';

const BASE_URL = '/api/tournaments/';

@Injectable({
  providedIn: 'root'
})
export class TournamentService {

  constructor(private http: HttpClient) {
  }

  getTournament(id: number | string) {
    return this.http.get(BASE_URL + id, { withCredentials: true }).pipe(map(
      response => response as Tournament,
      error => {
        if (error.status != 404) {
          console.error('Unexpected Error on getTournament')
          return false
        } else {
          return true
        }
      }
    ))
  }

  getTournaments(page: number | string) {
    return this.http.get(BASE_URL + "?page=" + page, { withCredentials: true }).pipe(map(
      response => getInfoPageableTournament(response),
      _error => console.error('Unexpected Error on getTournaments')
    ));
  }

  getTournamentTeams(id: number | string, name: string) {
    return this.http.get(BASE_URL + id + "/teams", { withCredentials: true }).pipe(map(
      response => participantsMapping(response as Team[], name),
      _error => console.error('Unexpected Error on getTournamentTeams')
    ))
  }

  getTournamentRound(id: number | string, round: number | string) {
    return this.http.get(BASE_URL + id + "/matches?round=" + round, { withCredentials: true }).pipe(map(
      response => response as Match[],
      error => errorIgnore(error, 400, "deleteTournament")
    ));
  }

  createTournament(Tournament: Tournament) {
    return this.http.post(BASE_URL, Tournament, { withCredentials: true }).pipe(map(
      response => response as Tournament,
      error => errorIgnore(error, 403, "createTournament")
    ));
  }

  updateTournament(tournament: Tournament) {
    return this.http.put(BASE_URL + tournament.id, tournament).pipe(map(
      response => response,
      error => errorIgnore(error, 400, "updateTournament")
    ));
  }

  deleteTeam(idTournament: number | string, idTeam: number | string) {
    return this.http.delete(BASE_URL + idTournament + "/teams/" + idTeam, { withCredentials: true });
  }

  inscription(id: number | string, user2: User) {
    return this.http.put(BASE_URL + id + "/teams", user2);
  }

  deleteTournament(id: number){
    return this.http.delete(BASE_URL + id).pipe(map(
      response => response,
      error => errorIgnore(error, 400, "deleteTournament")
    ))
  }

  getTournamentImage(id: number, aux: number) {
    return BASE_URL + id + "/image?" + aux
  }

  setTournamentImage(id:number, formData: FormData) {
    return this.http.post(BASE_URL +id+ '/image', formData).pipe(map(
      response => response,
      _error => console.error('Unexpected error in setTournamentImage')
    ))
  }

  deleteTournamentImage(id:number){
    return this.http.delete(BASE_URL +id+ '/image')
      .pipe(map(
        response => response,
        error => errorIgnore(error, 400, "deleteTournamentImage")
      ));
  }

  private handleError(error: any) {
    console.log("ERROR:");
    console.error(error);
    return throwError("Server error (" + error.status + ")")
  }

}

function getInfoPageableTournament(response: any): [Tournament[], boolean] {
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

//Didnt work, idk. Should debug to know

function participantsMapping(teamList: Team[], name: string): [Team[], boolean] {
  let isSigned = false;
  for (let i = 0; i < teamList.length; i++){
    if (teamList[i].userA.name == name || teamList[i].userB.name == name){
      isSigned = true;
    }
  }
  return [teamList, isSigned]
}

