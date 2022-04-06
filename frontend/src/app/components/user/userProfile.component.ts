import { User } from './../../models/user.model';
import { Component, ViewChild } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { LoginService } from 'src/app/services/login.service';




@Component({
  templateUrl: './userProfile.component.html'
})

export class UserProfileComponent{
  constructor(public loginService: LoginService, public userService: UserService) { }
}
