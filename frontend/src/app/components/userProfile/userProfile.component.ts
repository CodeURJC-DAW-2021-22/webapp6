import { User } from '../../models/user.model';
import { Component, ViewChild } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { LoginService } from 'src/app/services/login.service';
import { ActivatedRoute, Router } from '@angular/router';




@Component({
  templateUrl: './userProfile.component.html'
})

export class UserProfileComponent{

  constructor(private router: Router,public loginService: LoginService, public userService: UserService,activatedRoute: ActivatedRoute) {
   }

  calculatedKarma( array : number[]){
    let j = 0;
      for(let i = 0; i < array.length;i++){
          j+=array[i];
      }
    return Math.round(j/array.length);
  }

  updateUserForm(event:any,fullName:string,location:string, country:string, phone:string,user:User){
      user.realName=fullName;
      user.location = location;
      user.country=country;
      user.phone = phone;
      this.userService.updateUser(user);
    }
}
