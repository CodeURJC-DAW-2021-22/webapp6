import { User } from '../../models/user.model';
import { Component, ViewChild } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { LoginService } from 'src/app/services/login.service';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(private router: Router,public loginService: LoginService, public userService: UserService) {

    this.getUserTournaments();
    this.getUserPairs();
  }

  calculatedKarma( array : number[]){
    let j = 0;
      for(let i = 0; i < array.length;i++){
          j+=array[i];
      }
    return Math.round(j/array.length);
  }

  getUserPairs() {
    this.pagePairs = this.pagePairs + 1;
    this.userService.getUserPairs(this.pagePairs).subscribe(
      listPairs => {
        if (listPairs.content != undefined) {
          this.userPairs = this.userPairs.concat(listPairs.content);
          this.hasMorePairs = !listPairs.last;
        } else {
          this.hasMorePairs = false;
        }
      },
      error => {
        if (error.status != 403) {
          console.error('Unexpected Error on getUserPairs')
        }
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

  updateUserForm(event:any,fullName:string,location:string, country:string, phone:string){
      let user = this.loginService.currentUser();
      user.realName=fullName;
      user.location = location;
      user.country=country;
      user.phone = phone;

      this.uploadImage();
      this.userService.updateUser(user);
      this.loginService.reqIsLogged();
      
    }

  hasImage(){
      return this.loginService.currentUser().image;
  }

  uploadImage(): void {

    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.userService.setUserImage(formData).subscribe(
        _ => this.afterUploadImage(),
        error => alert('Error uploading user image: ' + error)
      );
    } else if(this.removeImage){
      this.userService.deleteUserImage().subscribe(
        _ => this.afterUploadImage(),
        error => alert('Error deleting user image: ' + error)
      );
    } else {
      this.afterUploadImage();
    }
  }

  private afterUploadImage(){
    this.router.navigate(['/user_profile']);
  }
}
