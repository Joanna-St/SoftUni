function attachEvents() {
    const BASE_URL = 'http://localhost:3030/jsonstore/forecaster/';
    let inputButton = document.getElementById('submit');
    inputButton.addEventListener('click', getWeather);


    async function getWeather() {
        let forecastDiv = document.getElementById('forecast');
        let currentForecastDiv = document.getElementById('current');
        let upcomingForecastDiv = document.getElementById('upcoming');

        currentForecastDiv.innerHTML = '<div class="label">Current conditions</div>'
        upcomingForecastDiv.innerHTML = '<div class="label">Three-day forecast</div>'

        let inputField = document.getElementById('location');
        let inputLocation = inputField.value;
        let locationCode = 'dummy';
        let locationFullName = '';
        let currentForecast = null;
        let upcomingForecasts = null;

        let resLocations = await fetch(`${BASE_URL}locations`)
        let allLocations = await resLocations.json();


        for (const location of allLocations) {
            if (location.name == inputLocation) {
                locationCode = location.code;
            }
        }


        try {
            let resForecastToday = await fetch(`${BASE_URL}today/${locationCode}`);
            let forecastToday = await resForecastToday.json();
            currentForecast = forecastToday.forecast;
            locationFullName = forecastToday.name;

            let resForecastUpcoming = await fetch(`${BASE_URL}upcoming/${locationCode}`);
            let forecastUpcoming = await resForecastUpcoming.json();
            upcomingForecasts = [...forecastUpcoming.forecast];
        } catch (error) {
            forecastDiv.style.display = 'block';
            currentForecastDiv.innerHTML += 'Error'
            upcomingForecastDiv.innerHTML += 'Error'
            return;
        }

        const symbols = {
            "Sunny": "\u2600",
            "Partly sunny": "\u26C5",
            "Overcast": "\u2601",
            "Rain": "\u2614"
        };

        const degrees = "&#176";


        forecastDiv.style.display = 'block';

        currentForecastDiv.innerHTML += '<div class="forecasts">' +
            '<span class="condition symbol"></span>' +
            '<ul class="condition" style="list-style-type: none">' +
            '<li></li>' +
            '<li></li>' +
            '<li></li>' +
            '</ul>' +
            '</div>'

        let currentLi = document.querySelector('#current > div.forecasts > ul').children;
        currentLi[0].textContent = locationFullName;
        currentLi[1].innerHTML = `${currentForecast.low}${degrees}/${currentForecast.high}${degrees}`
        currentLi[2].textContent = currentForecast.condition;
        let currentSymbol = document.querySelector('#current > div.forecasts > span');
        currentSymbol.innerText = symbols[currentForecast.condition];

        upcomingForecastDiv.innerHTML += '<div class="forecast-info">' +
            '<ul style="list-style-type: none">' +
            '<li class="upcoming">' +
            '<span class="symbol"></span>' +
            '<p></p>' +
            '</li>' +
            '<li class="upcoming">' +
            '<span class="symbol"></span>' +
            '<p></p>' +
            '</li>' +
            '<li class="upcoming">' +
            '<span class="symbol"></span>' +
            '<p></p>' +
            '</li>' +
            '</ul>' +
            '</div>'

        let upcomingLi = document.querySelector('#upcoming > div.forecast-info > ul').children;

        for (let index = 0; index < 3; index++) {
            const dayForecast = upcomingForecasts[index];
            upcomingLi[index].children[0].innerHTML = symbols[dayForecast.condition]
            upcomingLi[index].children[1].innerHTML = `${dayForecast.low}${degrees}/${dayForecast.high}${degrees}<br>${dayForecast.condition}`
        }

    }
}

attachEvents();