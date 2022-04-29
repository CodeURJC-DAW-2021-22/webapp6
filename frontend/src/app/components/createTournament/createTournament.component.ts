import { Router } from '@angular/router';
import { Tournament } from './../../models/tournament.model';
import { TournamentService } from './../../services/tournament.service';
import { Component, ViewChild } from '@angular/core';

import { LoginService } from 'src/app/services/login.service';


@Component({
  templateUrl: './createTournament.component.html'
})

export class CreateTournamentComponent {

  @ViewChild("file")
  file: any;

  constructor(private router: Router, private tournamentService: TournamentService,public loginService: LoginService){}

  TournamentCreated(tournamentName:string,about:string,ruleset:string,location:string,numParticipants:string,
    inscriptionDate:string,startDate:string){

      console.log(numParticipants)
      var numPart: number = +numParticipants
      console.log(numPart)

      const tournament: Tournament ={
        owner:"someone",
        tournamentName:tournamentName,
        numParticipants: numPart,
        numSignedUp:0,
        rounds:0,
        about:about,
        ruleset:ruleset,
        location:location,
        inscriptionDate:inscriptionDate,
        startDate:startDate,
        started:false,
        image:false
      }

      this.tournamentService.createTournament(tournament).subscribe(
        tournamentCreated => {
          this.uploadImage(tournamentCreated.id)
        }
      );
  }

  uploadImage(id: number): void {

    const image = this.file.nativeElement.files[0];
    if(image){
      let formData = new FormData();
      formData.append("imageFile", image);
      this.tournamentService.setTournamentImage(id, formData).subscribe(
        _ => this.router.navigate(['']),
        _error => this.router.navigate([''])
      );
    }
    else {
      this.router.navigate([''])
    }
  }
}
