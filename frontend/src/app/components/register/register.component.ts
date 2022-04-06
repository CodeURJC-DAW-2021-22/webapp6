import { User } from './../../models/user.model';
import { Component, ViewChild } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  templateUrl: './register.component.html'
})


export class RegisterComponent {
  newUser: boolean | undefined;
  User: User | undefined;
  // @ViewChild("file")
  // file: any;

  removeImage:boolean | undefined;

  constructor(private router: Router,activatedRoute: ActivatedRoute,private service: UserService)
  {
    // const id = activatedRoute.snapshot.params['id'];
    // if (id) {
    //   service.getUser(id).subscribe(
    //     User => this.User = User,
    //     error => console.error(error)
    //   );
    //   this.newUser = false;
    // } else {
    //   this.User = { name: '', realName: '',email:'', location:'',country:'', phone:'', numWins:0,numLoses:0,numMatchesPlayed:0,
    //   historicalKarma:[],status:false,encodedPassword:'',roles:[], image: false };
    //   this.newUser = true;
    // }
  }

  register(event: any, name: string,realName:string,email:string,password:string){
    if(!this.User){

      this.User = { name: name, realName: realName,email:email, location:'',country:'', phone:'', numWins:0,numLoses:0,numMatchesPlayed:0,
      historicalKarma:[500],status:false,encodedPassword:password,roles:[], image: false };


    if(this.User.image && this.removeImage){
      this.User.image = false;
    }
    this.service.registerUser(this.User).subscribe(
      error => alert('Error creating new User: ' + error));

    }else{
      throw Error();
    }
  }



}
