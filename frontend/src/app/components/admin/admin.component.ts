import { TournamentService } from './../../services/tournament.service';

import { Component } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { LoginService } from 'src/app/services/login.service';
import { Tournament } from 'src/app/models/tournament.model';
import { User } from 'src/app/models/user.model';

@Component({
  templateUrl: './admin.component.html'
})
export class AdminComponent {
   tournaments: Tournament[] = [];
  hasMoreTournaments: boolean = true;
  page: number = -1;

  usersPage = -1;
  hasMoreUsers: boolean = true;
  usersList: User[] = [];

  constructor(public loginService: LoginService, public userService: UserService, public tournamentService:TournamentService) {
    this.getTournaments();
    this.getUsers();
  }

  getUsers(){
    this.usersPage = this.usersPage + 1;
    this.userService.getAllUsers(this.usersPage).subscribe(
      info => {
        this.usersList = this.usersList.concat(info[0]);
        this.hasMoreUsers = info[1];
      }
    )
  }

  getTournaments() {
    this.page = this.page + 1;
    this.tournamentService.getTournaments(this.page).subscribe(
      info => {
        this.tournaments = this.tournaments.concat(info[0]);
        this.hasMoreTournaments = info[1];
      }
    )
  }

  hasImage(){
    return this.loginService.currentUser().image;
  }

 isAdmin(){
   return this.loginService.currentUser().roles.length=2;
 }

 removeUser(id:number){
  this.userService.deleteUser(id).subscribe(
    _ => {
      this.usersPage = -1;
      this.hasMoreUsers = true;
      this.usersList = [];
      this.getUsers();
    }
  )
 }

 removeTournament(id : number){
  this.tournamentService.deleteTournament(id).subscribe(
    _ => {
      this.tournaments=  [];
      this.hasMoreTournaments= true;
      this.page=-1;
      this.getTournaments();
    }
  )
 }



}
