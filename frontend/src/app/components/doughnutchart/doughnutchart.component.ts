import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
 import {AppConfigService} from '../../services/appconfigservice' ;
 import {AppConfig} from '../../appconfig' ;

@Component({
    templateUrl: './doughnutchart.component.html'
})
export class DoughnutChartComponent implements OnInit, OnDestroy {

    data: any;

    chartOptions: any;

   subscription: Subscription;

    config: AppConfig;

     constructor(private configService: AppConfigService) {}

    ngOnInit() {
        this.data = {
            labels: ['Victorias','Derrotas'],
            datasets: [
                {
                    data: [300, 50, ],
                    backgroundColor: [
                        "#FF6384",
                        "#36A2EB",

                    ],
                    hoverBackgroundColor: [
                        "#FF6384",
                        "#36A2EB",

                    ]
                }
            ]
        };

        this.config = this.configService.config;
        this.updateChartOptions();
        this.subscription = this.configService.configUpdate$.subscribe(config => {
            this.config = config;
            this.updateChartOptions();
        });
    }

    updateChartOptions() {
        this.chartOptions = this.config && this.config.dark ? this.getDarkTheme() : this.getLightTheme();
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

    ngOnDestroy() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }
}
