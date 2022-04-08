import { User } from './../../models/user.model';
import { Tournament } from './../../models/tournament.model';
import { TournamentService } from 'src/app/services/tournament.service';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  templateUrl: './tournament.component.html'
})
export class TournamentComponent {

  tournament: Tournament | undefined;
  userInTournament: boolean = false;
  tournamentOwner: boolean = false;
  usersPage = 0;
  hasMoreUsers: boolean = true;
  usersList: User[] = [];


  constructor(private router: Router, activatedRoute: ActivatedRoute, public tournamentService: TournamentService,
    public userService: UserService, public loginService: LoginService) {

    const id = activatedRoute.snapshot.params['id'];
    tournamentService.getTournament(id).subscribe(
      tournament => {
        this.tournament = tournament;
      },
      error => {
        if (error.status != 404) {
          console.error('Unexpected Error on getTournament')
        } else {
          this.router.navigate(['/error404'])
        }
      }
    )
    this.userInTournament = this.isUserInTournament(this.getThisUserTournaments());
    this.tournamentOwner = this.isTournamentOwner();
  }

  isTournamentOwner(){
    if (this.loginService.isLogged()) {
      return (this.loginService.currentUser().name == this.tournament?.owner)
    } else {
      return false;
    }
  }

  canTournamenStart(){
    if (this.tournament?.numSignedUp !== undefined){
      return (this.tournament?.numSignedUp > 1);
    } else {
      return false;
    }
  }

  isUserInTournament(tournaments: Tournament[]): boolean {
    if (this.loginService.isLogged()) {
      for (let i = 0; i < tournaments.length; i++){
        if (tournaments[i]?.id == this.tournament?.id){
          return true;
        }
      }
      tournaments.length
    }
    return false;
  }

  getThisUserTournaments(): Tournament[] {
    let tournaments: Tournament[] = []
    let page = 0
    let hasMoreTournaments = true;
    if (this.loginService.isLogged()) {
      while (hasMoreTournaments){
        this.userService.getUserTournaments(page).subscribe(
          listTournaments => {
            if (listTournaments.content != undefined) {
              tournaments = tournaments.concat(listTournaments.content);
              hasMoreTournaments = !listTournaments.last;
            } else {
              hasMoreTournaments = false;
            }
          },
          error => {
            if (error.status != 403) {
              console.error('Unexpected Error on getUserTournaments')
            }
          }
        )
      }
    }
    return tournaments;
  }

  resetUsersPage(){
    this.usersPage = 0;
    this.hasMoreUsers = true;
    this.usersList = [];
    this.getThisUserTournaments();
  }

  getUsersForInscription(){
    this.usersPage = this.usersPage + 1;
    this.userService.getAllUsers(this.usersPage).subscribe(
      listUsers => {
        if (listUsers.content != undefined) {
          this.usersList = this.usersList.concat(listUsers.content);
          this.hasMoreUsers = !listUsers.last;
        } else {
          this.hasMoreUsers = false;
        }
      },
      error => {
        console.error('Unexpected Error on getUsersForInscription')
      }
    )
  }

  inscription(id: number | undefined){

  }

  startTournament(){

  }


  //
  //IF INSCRIPTION OR DELETE TEAM WENT SUCCESFULLY ->  this.router.navigate(['/tournament', this.tournament.id]);
  //BECAUSE VAR USERINTOURNAMENT HAS TO BE UPDATED BY CONTRUCTOR
  //

}
