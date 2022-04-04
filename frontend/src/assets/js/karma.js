var historicalKarmaRaw = document.querySelector("#historicalKarma").textContent;
let historicalKarmaString = historicalKarmaRaw.replace("[", "").replace("]", "")
let historicalKarmaArray = historicalKarmaString.split(",")

document.addEventListener("DOMContentLoaded", () => {
    echarts.init(document.querySelector("#lineChart")).setOption({
        xAxis: {
            type: 'category',
            data: ['', '', '', '', '', '', '', '', '', '']
        },
        yAxis: {
            type: 'value'
        },
        color: ['#67d400', '#121212', '#fac858', '#ee6666', '#73c0de', '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc'],
        series: [{
            data: [
                historicalKarmaArray[0], historicalKarmaArray[1], historicalKarmaArray[2], historicalKarmaArray[3],
                historicalKarmaArray[4], historicalKarmaArray[5], historicalKarmaArray[6], historicalKarmaArray[7],
                historicalKarmaArray[8], historicalKarmaArray[9]
            ],
            type: 'line',
            smooth: true
        }]
    });
});