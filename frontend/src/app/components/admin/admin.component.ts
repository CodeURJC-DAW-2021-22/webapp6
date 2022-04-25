import { TournamentService } from './../../services/tournament.service';

import { Component, ViewChild } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { LoginService } from 'src/app/services/login.service';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(private router: Router,public loginService: LoginService, public userService: UserService, public tournamentService:TournamentService) {
    this.getTournaments();
    this.getUser();
  }

  getUser() {
    this.usersPage = this.usersPage + 1;
    this.userService.getAllUsers(this.usersPage).subscribe(
      listPairs => {
        if (listPairs.content != undefined) {
          this.usersList = this.usersList.concat(listPairs.content);
          this.hasMoreUsers = !listPairs.last;
        } else {
          this.hasMoreUsers = false;
        }
      },
      error => {
        if (error.status != 403) {
          console.error('Unexpected Error on getUserPairs')
        }
      }
    )
  }

  getTournaments() {
    this.page = this.page + 1;
    this.tournamentService.getTournaments(this.page).subscribe(
      listTournaments => {
        if (listTournaments.content != undefined) {
          this.tournaments = this.tournaments.concat(listTournaments.content);
          this.hasMoreTournaments = !listTournaments.last;
        } else {
          this.hasMoreTournaments = false;
        }
      },
      error => {
        console.error('Unexpected Error on getTournaments')
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
  this.userService.deleteUser(id);
  this.usersPage = -1;
  this.hasMoreUsers = true;
  this.usersList = [];
  this.getUser();
 }

 removeTournament(id : number){

 }



}
