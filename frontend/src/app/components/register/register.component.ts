import { User } from './../../models/user.model';
import { Component } from '@angular/core';
import { UserService } from 'src/app/services/user.service';


@Component({
  templateUrl: './register.component.html'
})


export class RegisterComponent {
  newUser: boolean | undefined;
  User: User | undefined;

  removeImage:boolean | undefined;

  constructor(private service: UserService){}

  register(name: string,realName:string,email:string,password:string){
    if(!this.User){

      this.User = { name: name, realName: realName,email:email, location:'',country:'', phone:'', numWins:0,numLoses:0,numMatchesPlayed:0,
      historicalKarma:[500],status:false,encodedPassword:password,roles:[], image: false };

    this.service.registerUser(this.User)
    }
    else {
      throw Error();
    }
  }
}
