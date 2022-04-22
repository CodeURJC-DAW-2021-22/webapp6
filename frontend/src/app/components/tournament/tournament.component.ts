import { Match } from './../../models/match.model';
import { Team } from './../../models/team.model';
import { User } from './../../models/user.model';
import { Tournament } from './../../models/tournament.model';
import { TournamentService } from 'src/app/services/tournament.service';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';
import { Observable } from 'rxjs';

@Component({
  templateUrl: './tournament.component.html'
})
export class TournamentComponent{

  id: number = 0;
  tournament!: Tournament;
  participants: Team[] = [];

  userInTournament: boolean | undefined;

  //Bracket Rounds
  round4: Match[] = [];
  round3: Match[] = [];
  round2: Match[] = [];
  round1: Match[] = [];

  //For User List on Inscription
  usersPage = -1;
  hasMoreUsers: boolean = true;
  usersList: User[] = [];


  constructor(private router: Router, activatedRoute: ActivatedRoute, public tournamentService: TournamentService,
    public userService: UserService, public loginService: LoginService) {

    const id = activatedRoute.snapshot.params['id'];
    this.id = id;

    this.getTournamentInit(id);
  }

  getTournamentInit(id: number | string) {
    this.tournamentService.getTournament(id).subscribe(
      tournament => {
        this.tournament = tournament;
        this.getParticipants();
        if (tournament.started) {
          this.getRound4();
          this.getRound3();
          this.getRound2();
          this.getRound1();
        }
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

  refreshTournament(id: number | string) {
    this.tournamentService.getTournament(id).subscribe(
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

  getParticipants() {
		this.tournamentService.getTournamentTeams(this.id).subscribe(
      teamList => {
        this.participants = teamList;
        let isSigned = false;
        if (this.loginService.isLogged()) {
          let name = this.loginService.currentUser().name;
          for (let i = 0; i < teamList.length; i++){
            if (teamList[i].userA.name == name || teamList[i].userB.name == name){
              isSigned = true;
            }
          }
        }
        this.userInTournament = isSigned;
      }
    );
	}

  getRound4() {
    this.tournamentService.getTournamentRound(this.id, 4).subscribe(
      matchList => {
        this.round4 = matchList;
      },
      error => {
        if (error.status != 400) {
          console.error('Unexpected Error on getTournament')
        }
      }
    )
  }

  getRound3() {
    this.tournamentService.getTournamentRound(this.id, 3).subscribe(
      matchList => {
        this.round3 = matchList;
      },
      error => {
        if (error.status != 400) {
          console.error('Unexpected Error on getTournament')
        }
      }
    )
  }

  getRound2() {
    this.tournamentService.getTournamentRound(this.id, 2).subscribe(
      matchList => {
        this.round2 = matchList;
      },
      error => {
        if (error.status != 400) {
          console.error('Unexpected Error on getTournament')
        }
      }
    )
  }

  getRound1() {
    this.tournamentService.getTournamentRound(this.id, 1).subscribe(
      matchList => {
        this.round1 = matchList;
      },
      error => {
        if (error.status != 400) {
          console.error('Unexpected Error on getTournament')
        }
      }
    )
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
    this.getTournamentInit(this.id);
  }

  startTournament(){
    const startedTournament : Tournament = {
      id: this.id,
      owner: '',
      tournamentName: '',
      numParticipants: 0,
      numSignedUp: 0,
      rounds: 0,
      about: '',
      ruleset: '',
      location: '',
      inscriptionDate: '2022-12-16T16:00',
      startDate: '2022-12-16T16:00',
      started: true,
      image: false
    }

    this.getTournamentInit(this.id);
  }

  deleteTeam(id: number | undefined){

  }


  updateTournamentForm(name:string, about:string, ruleset:string, location:string, inscriptionDate:string, startDate:string){

    let newinscriptionDate = ''
    let newstartDate = ''
    if (inscriptionDate != '') {
      newinscriptionDate = this.formatDate(inscriptionDate);
    } else {
      newinscriptionDate =  this.tournament.inscriptionDate;
    }
    if (startDate != '') {
      newstartDate = this.formatDate(startDate);
    } else {
      newstartDate =  this.tournament.startDate;
    }

    const updatedTournanent: Tournament = {
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
      started: false,
      //BE CAREFULL WITH THIS BOOLEAN WHEN UPDATED A IMG (NOT DONE YET)
      image: false
    }

    this.refreshTournament(this.id)
  }

  private formatDate(date: string): string {
    let splitted1 = date.split('T');
    let splitted2 = splitted1[0].split('-');
    let newDate = splitted1[1] + ' ' + ' ' + splitted2[2] + '/' + splitted2[1] + '/' + splitted2[0];
    return newDate;
    // "yyyy-MM-ddTHH:mm"
    // "HH:mm  dd/MM/yyyy"
  }

  private formatDateReverse(date: string): string {
    let splitted1 = date.split('  ');
    let splitted2 = splitted1[1].split('/');
    let newDate = splitted2[2] + '-' + splitted2[1] + '-' + splitted2[0] + 'T' + splitted1[0];
    return newDate;
    // "yyyy-MM-ddTHH:mm"
    // "HH:mm  dd/MM/yyyy"
  }


  //
  //IF INSCRIPTION OR DELETE TEAM WENT SUCCESFULLY ->  this.router.navigate(['/tournament', this.tournament.id]);
  //BECAUSE VAR USERINTOURNAMENT HAS TO BE UPDATED BY CONTRUCTOR
  //

}
