import { TournamentService } from 'src/app/services/tournament.service';
import { User } from '../../models/user.model';
import { Component, ViewChild } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { LoginService } from 'src/app/services/login.service';
import { Tournament } from 'src/app/models/tournament.model';




@Component({
  templateUrl: './userProfile.component.html'
})

export class UserProfileComponent{

  userTournaments: Tournament[] = [];
  hasMoreUserTournaments: boolean = true;
  pageUser: number = -1;

  userPairs: User[] = [];
  hasMorePairs: boolean = true;
  pagePairs: number = -1;

  @ViewChild("file")
  file: any;

  removeImage:boolean;
  auxURL: number = 1;

  constructor(public loginService: LoginService, public userService: UserService, public tournamentService: TournamentService) {

    this.getUserTournaments();
    this.getUserPairs();
  }

  getKarma(array : number[]){
    return array[array.length-1];
  }

  getUserPairs() {
    this.pagePairs = this.pagePairs + 1;
    this.userService.getUserPairs(this.pagePairs).subscribe(
      info => {
        this.userPairs = this.userPairs.concat(info[0]);
        this.hasMorePairs = info[1];
      }
    )
  }

  getUserTournaments() {
    this.pageUser = this.pageUser + 1;
    this.userService.getUserTournaments(this.pageUser).subscribe(
      info => {
        this.userTournaments = this.userTournaments.concat(info[0]);
        this.hasMoreUserTournaments = info[1];
      }
    )
  }

  updateUserForm(fullName:string,location:string, country:string, phone:string){
      let user = this.loginService.currentUser();
      user.realName=fullName;
      user.location = location;
      user.country=country;
      user.phone = phone;

      this.userService.updateUser(user).subscribe(
        _ => this.uploadImage()
      )
    }

  hasImage(){
      return this.loginService.currentUser().image;
  }

  uploadImage(): void {

    const image = this.file.nativeElement.files[0];
    if (this.removeImage) {
      this.userService.deleteUserImage().subscribe(
        _ => this.afterUploadImage()
      );
    } else if(image){
      let formData = new FormData();
      formData.append("imageFile", image);
      this.userService.setUserImage(formData).subscribe(
        _ => {
          this.afterUploadImage()
          this.auxURL = this.auxURL +1
        }
      );
    } else {
      this.afterUploadImage();
    }
  }

  private afterUploadImage(){
    this.loginService.reqIsLogged();
    this.removeImage = false;
  }
}
