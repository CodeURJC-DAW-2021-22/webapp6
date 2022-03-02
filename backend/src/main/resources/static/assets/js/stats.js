var wins = document.querySelector("#numWins").textContent;
var loses = document.querySelector("#numLoses").textContent;


document.addEventListener("DOMContentLoaded", () => {
  echarts.init(document.querySelector("#donutChart")).setOption({

    color: ['#67d400', '#121212'],
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: '5%',
      left: 'center'
    },
    series: [{
      name: 'Victorias - Derrotas',
      type: 'pie',
      radius: ['50%', '70%'],
      avoidLabelOverlap: false,
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: '18',
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: [{
        value: wins,
        name: 'Victorias',
      },
      {
        value: loses,
        name: 'Derrotas'
      }
      ]
    }]
  });
});