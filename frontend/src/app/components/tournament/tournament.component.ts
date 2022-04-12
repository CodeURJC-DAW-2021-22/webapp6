import { Match } from './../../models/match.model';
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

  //Bracket Rounds
  // $round4: Observable<Match[]> | undefined;
  // $round3: Observable<Match[]> | undefined;
  // $round2: Observable<Match[]> | undefined;
  // $round1: Observable<Match[]> | undefined;
  round4: Match[] | undefined;
  round3: Match[] | undefined;
  round2: Match[] | undefined;
  round1: Match[] | undefined;

  hasRound4: boolean = false;
  hasRound3: boolean = false;
  hasRound2: boolean = false;
  hasRound1: boolean = false;

  //For User List on Inscription
  usersPage = -1;
  hasMoreUsers: boolean = true;
  usersList: User[] = [];


  constructor(private router: Router, activatedRoute: ActivatedRoute, public tournamentService: TournamentService,
    public userService: UserService, public loginService: LoginService) {

    this.id = activatedRoute.snapshot.params['id'];
    const id = activatedRoute.snapshot.params['id'];

    //Set Tournament Requested
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

    this.getRounds(id);
  }

  ngOnInit() {
		this.refreshParticipants();
    // this.refreshMatches();
  }

  refreshParticipants() {
		this.$participants = this.tournamentService.getTournamentTeams(this.id);
	}

  // refreshMatches() {
  //   this.$round4 = this.tournamentService.getTournamentRound(this.id, 4);
  //   this.$round3 = this.tournamentService.getTournamentRound(this.id, 3);
  //   this.$round2 = this.tournamentService.getTournamentRound(this.id, 2);
  //   this.$round1 = this.tournamentService.getTournamentRound(this.id, 1);
  // }

  getRounds(id: number | string) {
    //Round 4
    this.tournamentService.getTournamentRound(id, 4).subscribe(
      round => {
        this.round4 = round;
        this.hasRound4 = true;
      },
      error => {
        if (error.status == 400) {
          this.round4 = [];
        } else {
          if (error.status != 404) {
            console.error('Unexpected Error on getTournament');
          }
        }
      }
    )

    //Round 3
    this.tournamentService.getTournamentRound(id, 3).subscribe(
      round => {
        this.round3 = round;
        this.hasRound3 = true;
      },
      error => {
        if (error.status == 400) {
          this.round3 = [];
        } else {
          if (error.status != 404) {
            console.error('Unexpected Error on getTournament');
          }
        }
      }
    )

    //Round 2
    this.tournamentService.getTournamentRound(id, 2).subscribe(
      round => {
        this.round2 = round;
        this.hasRound2 = true;
      },
      error => {
        if (error.status == 400) {
          this.round2 = [];
        } else {
          if (error.status != 404) {
            console.error('Unexpected Error on getTournament');
          }
        }
      }
    )

    //Round 1
    this.tournamentService.getTournamentRound(id, 1).subscribe(
      round => {
        this.round1 = round;
        this.hasRound1 = true;
      },
      error => {
        if (error.status == 400) {
          this.round1 = [];
        } else {
          if (error.status != 404) {
            console.error('Unexpected Error on getTournament');
          }
        }
      }
    )
  }

  loadHasRounds() {

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
