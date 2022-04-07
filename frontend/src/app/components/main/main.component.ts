import { LoginService } from 'src/app/services/login.service';
import { Tournament } from './../../models/tournament.model';
import { Component } from '@angular/core';
import { TournamentService } from 'src/app/services/tournament.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  templateUrl: './main.component.html'
})
export class MainComponent {

  userTournaments: Tournament[] = [];
  tournaments: Tournament[] = [];
  hasMoreTournaments: boolean = true;
  hasMoreUserTournaments: boolean = true;
  page: number = -1;
  pageUser: number = -1;

  constructor(public loginService: LoginService, public tournamentService: TournamentService, public userService: UserService) {
    this.getTournaments();
    this.getUserTournaments();
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

  getUserTournaments() {
    this.pageUser = this.pageUser + 1;
    this.userService.getUserTournaments(this.pageUser).subscribe(
      listTournaments => {
        if (listTournaments.content != undefined) {
          this.userTournaments = this.userTournaments.concat(listTournaments.content);
          this.hasMoreUserTournaments = !listTournaments.last;
        } else {
          this.hasMoreUserTournaments = false;
        }
      },
      error => {
        if (error.status != 403) {
          console.error('Unexpected Error on getUserTournaments')
        }
      }
    )
  }

  // hasTournamentImage(id: number | undefined){
  //   let image = this.tournamentService.getTournamentImage(id);
  //   if (image === undefined) {
  //     return false;
  //   } else {
  //     return true;
  //   }
  // }

  // getTournamentImage(id: number | undefined){
  //   return this.tournamentService.getTournamentImage(id);;
  // }

  // hasTournamentImage(id: number | undefined){
  //   let has = false
  //   this.tournamentService.getTournamentImageAPI(id).subscribe(data => {
  //     has = true
  //   }, error => {
  //     has = false
  //   });
  //   return has;
  // }

//   getTournamentImage(id: number | undefined) {
//     let imageToShow: any;
//     this.tournamentService.getTournamentImageAPI(id).subscribe(data => {
//       imageToShow = this.createImageFromBlob(data);
//     }, error => {
//       console.error('Unexpected Error on hasTournamentImage')
//     });
//     return imageToShow;
//   }

//   createImageFromBlob(image: Blob) {
//     let imageToShow: any;
//     let reader = new FileReader();
//     reader.addEventListener("load", () => {
//        imageToShow = reader.result;
//     }, false);

//     if (image) {
//        reader.readAsDataURL(image);
//     }
//     return imageToShow;
//  }
}
