import { User } from 'src/app/models/user.model';
import { LoginService } from 'src/app/services/login.service';
import{Component} from '@angular/core';

@Component({
  selector: 'lineChart',
  templateUrl: './linechart.component.html'
})

export class LineChartComponent {

  basicData: any;
  basicOptions: any;

  constructor(public loginService: LoginService) { }


  ngOnInit() {
    this.loginService.fetchCurrentUser().subscribe(
      response => {
        let user = response as User;

        console.log(user);
      this.basicData = {
          labels: ['', '', '', '', '', '', '','','',''],
          datasets: [
              {
                  label: 'Karma Set',
                  data: [user.historicalKarma[0],user.historicalKarma[1],user.historicalKarma[2],
                  user.historicalKarma[3],user.historicalKarma[4],user.historicalKarma[5],user.historicalKarma[6],
                  user.historicalKarma[7],user.historicalKarma[8],user.historicalKarma[9]],
                  fill: false,
                  borderColor: '#42A5F5',
                  tension: .4
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



  applyLightTheme() {
      this.basicOptions = {
          plugins: {
              legend: {
                  labels: {
                      color: '#495057'
                  }
              }
          },
          scales: {
              x: {
                  ticks: {
                      color: '#495057'
                  },
                  grid: {
                      color: '#ebedef'
                  }
              },
              y: {
                  ticks: {
                      color: '#495057'
                  },
                  grid: {
                      color: '#ebedef'
                  }
              }
          }
      };


  }

  applyDarkTheme() {
      this.basicOptions = {
          plugins: {
              legend: {
                  labels: {
                      color: '#ebedef'
                  }
              }
          },
          scales: {
              x: {
                  ticks: {
                      color: '#ebedef'
                  },
                  grid: {
                      color: 'rgba(255,255,255,0.2)'
                  }
              },
              y: {
                  ticks: {
                      color: '#ebedef'
                  },
                  grid: {
                      color: 'rgba(255,255,255,0.2)'
                  }
              }
          }
      };


  }
}
