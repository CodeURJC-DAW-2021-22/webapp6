import { Team } from './../../models/team.model';
import { User } from './../../models/user.model';
import { Tournament } from './../../models/tournament.model';
import { TournamentService } from 'src/app/services/tournament.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';
import { Observable } from 'rxjs';

@Component({
  templateUrl: './tournament.component.html'
})
export class TournamentComponent implements OnInit{

  id: number = 0;
  tournament: Tournament | undefined;
  $participants: Observable<Team[]> | undefined;
  userInTournament: boolean | undefined;

  userName: string | undefined;

  usersPage = -1;
  hasMoreUsers: boolean = true;
  usersList: User[] = [];


  constructor(private router: Router, activatedRoute: ActivatedRoute, public tournamentService: TournamentService,
    public userService: UserService, public loginService: LoginService) {

    this.id = activatedRoute.snapshot.params['id'];
    const id = activatedRoute.snapshot.params['id'];
    if (this.loginService.isLogged()) {
      this.userName = this.loginService.currentUser().name;
    }

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
  }

  ngOnInit() {
		this.refresh();
    this.tournamentService
  }

  refresh() {
		this.$participants = this.tournamentService.getTournamentTeams(this.id);
	}

  checkUser(name: String) {
    if (!this.userInTournament) {
      this.userInTournament = name == this.loginService.currentUser().name;
    }
    return true;
  }

  canTournamenStart(){
    if (this.tournament?.numSignedUp !== undefined){
      return (this.tournament?.numSignedUp > 1);
    } else {
      return false;
    }
  }

  resetUsersPage(){
    this.usersPage = 0;
    this.hasMoreUsers = true;
    this.usersList = [];
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
    //...
  }

  startTournament(){

  }

  deleteTeam(id: number | undefined){

  }


  //
  //IF INSCRIPTION OR DELETE TEAM WENT SUCCESFULLY ->  this.router.navigate(['/tournament', this.tournament.id]);
  //BECAUSE VAR USERINTOURNAMENT HAS TO BE UPDATED BY CONTRUCTOR
  //

}
