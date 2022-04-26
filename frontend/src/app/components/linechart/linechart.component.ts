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




  ngOnInit() {
      this.basicData = {
          labels: ['', '', '', '', '', '', '','','',''],
          datasets: [
              {
                  label: 'Karma Set',
                  data: [65, 59, 80, 81, 56, 55, 40,20,100,200],
                  fill: false,
                  borderColor: '#42A5F5',
                  tension: .4
              }

          ]
      };




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
