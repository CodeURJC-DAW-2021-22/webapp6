import { TournamentService } from './../../services/tournament.service';
import { Component, ViewChild } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';


@Component({
  templateUrl: './createTournament.component.html'
})

export class CreateTournamentComponent {

  constructor(private router: Router,activatedRoute: ActivatedRoute,private service: TournamentService){}

  createTournament(){

  }


}
