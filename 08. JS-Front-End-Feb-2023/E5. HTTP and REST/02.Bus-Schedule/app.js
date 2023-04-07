function solve() {
    const BASE_URL = 'http://localhost:3030/jsonstore/bus/schedule/';
    let stopInfo = document.querySelector('.info');
    let departButton = document.getElementById('depart');
    let arriveButton = document.getElementById('arrive');
    let nextStopID = '';
    let nextStopName = ''
    let currentStopID = '';
    let currentStopName = '';

    function depart() {

        if (currentStopID == '') {
            nextStopID = 'depot'
        }

        fetch(`${BASE_URL}${nextStopID}`)
            .then(res => res.json())
            .then(stop => {
                nextStopID = stop.next;
                nextStopName = stop.name;
                stopInfo.textContent = `Next stop ${nextStopName}`;
            })
            .catch(() => {
                stopInfo.textContent = 'Error'
                departButton.disabled = 'true';
                arriveButton.disabled = 'true';
            })
        
        departButton.disabled = 'true';
        arriveButton.disabled = '';
    }

    async function arrive() {
        currentStopName = nextStopName;
        currentStopID = nextStopID;

        stopInfo.textContent = `Arriving at ${currentStopName}`;
        departButton.disabled = '';
        arriveButton.disabled = 'true';
    }

    return {
        depart,
        arrive
    };
}

let result = solve();