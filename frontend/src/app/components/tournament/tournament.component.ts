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
  $tournament: Observable<Tournament> | undefined;
  $participants: Observable<Team[]> | undefined;

  userInTournament: boolean | undefined;

  //Bracket Rounds
  // $round4: Observable<Match[]> | undefined;
  // $round3: Observable<Match[]> | undefined;
  // $round2: Observable<Match[]> | undefined;
  // $round1: Observable<Match[]> | undefined;
  $round4: Observable<Match[]> | undefined;
  $round3: Observable<Match[]> | undefined;
  $round2: Observable<Match[]> | undefined;
  $round1: Observable<Match[]> | undefined;

  //For User List on Inscription
  usersPage = -1;
  hasMoreUsers: boolean = true;
  usersList: User[] = [];


  constructor(private router: Router, activatedRoute: ActivatedRoute, public tournamentService: TournamentService,
    public userService: UserService, public loginService: LoginService) {

    this.id = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.getTournament(this.id);
		this.getParticipants();
    this.getRounds(this.id);
  }

  getParticipants() {
		this.$participants = this.tournamentService.getTournamentTeams(this.id);
	}

  refreshParticipantsWhenFinish(items: Observable<any>) {
		items.subscribe(_ => this.getParticipants());
	}

  getTournament(id: number | string) {
    this.$tournament = this.tournamentService.getTournament(id);
  }

  refreshTournamentWhenFinish(items: Observable<any>) {
    items.subscribe(_ => this.getTournament(this.id));
	}

  getRounds(id: number | string) {
    this.$round4 =  this.tournamentService.getTournamentRound(id, 4);
    this.$round3 =  this.tournamentService.getTournamentRound(id, 3);
    this.$round2 =  this.tournamentService.getTournamentRound(id, 2);
    this.$round1 =  this.tournamentService.getTournamentRound(id, 1);
  }

  refreshRoundsWhenFinish(items: Observable<any>) {
		items.subscribe(_ => this.getRounds(this.id));
    // items.subscribe(_ => this.getTournament(this.id));
	}

  checkUser(name: String) {
    if (!this.userInTournament && this.loginService.isLogged()) {
      this.userInTournament = name == this.loginService.currentUser().name;
    }
    return true;
  }

  getName() {
    if (this.loginService.isLogged()){
      return this.loginService.currentUser().name;
    }
    return "";
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
    const user2 : User = {
      id: id,
      name: '',
      email: '',
      realName: '',
      location: '',
      country: '',
      phone: '',
      numWins: 0,
      numLoses: 0,
      numMatchesPlayed: 0,
      historicalKarma: [],
      status: false,
      image: false,
      encodedPassword: '',
      roles: []
    }
    this.refreshParticipantsWhenFinish(this.tournamentService.inscription(this.id, user2));
  }

  //idk why it doesnt work
  startTournament(){
    const newTourn : Tournament = {
      id: this.id,
      owner: '',
      tournamentName: '',
      numParticipants: 0,
      numSignedUp: 0,
      rounds: 0,
      about: '',
      ruleset: '',
      location: '',
      inscriptionDate: '',
      startDate: '',
      started: true
    }

    this.refreshRoundsWhenFinish(this.tournamentService.updateTournament(newTourn));
  }

  deleteTeam(id: number | undefined){

  }

  //It works but idk why it gives a 400 error (100% same JSON input than Postman)
  updateTournamentForm(name:string, about:string, ruleset:string, location:string, inscriptionDate:string, startDate:string){

    let newinscriptionDate = ''
    let newstartDate = ''
    if (inscriptionDate != '') {
      newinscriptionDate = this.formatDate(inscriptionDate);
    }
    if (startDate != '') {
      newstartDate = this.formatDate(startDate);
    }

    let newTourn: Tournament = {
      id: this.id,
      owner: '',
      tournamentName: name,
      numParticipants: 0,
      numSignedUp: 0,
      rounds: 0,
      about: about,
      ruleset: ruleset,
      location: location,
      inscriptionDate: newinscriptionDate,
      startDate: newstartDate,
      started: false
    }

    this.refreshTournamentWhenFinish(this.tournamentService.updateTournament(newTourn))
  }

  private formatDate(date: string): string {
    let splitted1 = date.split('T');
    let splitted2 = splitted1[0].split('-');
    let newDate = splitted1[1] + ' ' + ' ' + splitted2[2] + '/' + splitted2[1] + '/' + splitted2[0];
    return newDate;
    // "yyyy-MM-ddTHH:mm"
    // "HH:mm  dd/MM/yyyy"
  }


  //
  //IF INSCRIPTION OR DELETE TEAM WENT SUCCESFULLY ->  this.router.navigate(['/tournament', this.tournament.id]);
  //BECAUSE VAR USERINTOURNAMENT HAS TO BE UPDATED BY CONTRUCTOR
  //

}
