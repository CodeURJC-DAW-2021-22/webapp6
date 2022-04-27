import { Tournament } from './../../models/tournament.model';
import { TournamentService } from './../../services/tournament.service';
import { Component } from '@angular/core';

import { LoginService } from 'src/app/services/login.service';


@Component({
  templateUrl: './createTournament.component.html'
})

export class CreateTournamentComponent {
  Tournament :Tournament | undefined

  constructor(private service: TournamentService,public loginService: LoginService){}

  TournamentCreated(event:any,tournamentName:string,
    about:string,ruleset:string,location:string,numParticipants:string,inscriptionDate:string,startDate:string){


      this.Tournament={owner:"someone",tournamentName:tournamentName,numParticipants:Number(numParticipants),numSignedUp:0,rounds:0,about:about,
      ruleset:ruleset,location:location, inscriptionDate:inscriptionDate,startDate:startDate,started:false,image:false}


      this.service.createTournament(this.Tournament);




  }


}
