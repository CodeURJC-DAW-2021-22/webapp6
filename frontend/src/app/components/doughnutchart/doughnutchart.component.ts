import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import { User } from 'src/app/models/user.model';
import { LoginService } from 'src/app/services/login.service';


@Component({
    selector: 'doughnutChart',
    templateUrl: './doughnutchart.component.html'
})

export class DoughnutChartComponent  {

    data: any;

    chartOptions: any;

   subscription: Subscription;



   constructor(public loginService: LoginService) { }


    ngOnInit() {
      this.loginService.fetchCurrentUser().subscribe(
        response => {
          let user = response as User;

          console.log(user);

          this.data = {
              labels: ['Victorias','Derrotas'],
              datasets: [
                  {
                      data: [user.numWins+1, user.numLoses ],
                      backgroundColor: [
                          "#67d400",
                          "#121212",

                      ],
                      hoverBackgroundColor: [
                          "#00C851",
                          "#121212",



                      ]
                  }
              ]
          };



        },
        error => {
          if (error.status != 403) {
              console.error('Error when asking if logged: ' + JSON.stringify(error));
          }
        }
      );



    }



    getLightTheme() {
        return {
            plugins: {
                legend: {
                    labels: {
                        color: '#495057'
                    }
                }
            }
        }
    }

    getDarkTheme() {
        return {
            plugins: {
                legend: {
                    labels: {
                        color: '#ebedef'
                    }
                }
            }
        }
    }


}
