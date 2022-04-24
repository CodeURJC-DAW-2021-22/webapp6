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

import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

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

    this.getTournamentInit(id, 1);
  }

  getTournamentInit(id: number | string, aux: any) {
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

  refreshTournament(id: number | string, aux: any) {
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
    this.usersPage = -1;
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

    this.getTournamentInit(this.id, this.tournamentService.inscription(this.id, user2).subscribe());
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

    this.getTournamentInit(this.id, this.tournamentService.updateTournament(startedTournament).subscribe());
  }

  deleteTeam(idTeam: number | undefined){
    if (idTeam !== undefined){
      this.getTournamentInit(this.id, this.tournamentService.deleteTeam(this.id, idTeam).subscribe());
    }
  }


  updateTournamentForm(name:string, about:string, ruleset:string, location:string, inscriptionDate:string, startDate:string){

    let newinscriptionDate = ''
    let newstartDate = ''
    if (inscriptionDate != '') {
      newinscriptionDate = inscriptionDate;
    } else {
      newinscriptionDate =  this.formatDateReverse(this.tournament.inscriptionDate);
    }
    if (startDate != '') {
      newstartDate = startDate;
    } else {
      newstartDate = this.formatDateReverse(this.tournament.startDate);
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

    this.getTournamentInit(this.id, this.tournamentService.updateTournament(updatedTournanent).subscribe())
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

  createPDF(){
    var PdfDefinition:any={
      watermark: 'PADELANTE',
      pageOrientation: 'landscape',
      content:[
        {
          text:'Enhorabuena Padelero por participar en los torneos organizados por PADELANTE',

          style: 'header',
          alignment: 'center',

        },
        '\n',
        {
          image:'data:image/png;base64,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',
          width: 400,
			  height: 400,
        alignment: 'center',

        },'\n',
          {
            text:'Para jugar mas torneos visita nuestra pagina web',
            style: 'header',
            alignment: 'center',

          },
      ],styles: {
        header: {
          fontSize: 18,
          bold: true
        }
      }
    }
    const pdf= pdfMake.createPdf(PdfDefinition);
    pdf.open();
  }

  //
  //IF INSCRIPTION OR DELETE TEAM WENT SUCCESFULLY ->  this.router.navigate(['/tournament', this.tournament.id]);
  //BECAUSE VAR USERINTOURNAMENT HAS TO BE UPDATED BY CONTRUCTOR
  //

}
