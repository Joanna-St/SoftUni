function getInfo() {
    const BASE_URL = 'http://localhost:3030/jsonstore/bus/businfo/';
    let stopId = document.getElementById('stopId').value;
    let stopNameDiv = document.getElementById('stopName');
    let busesTable = document.getElementById('buses')
    busesTable.innerHTML = '';
    
    fetch(`${BASE_URL}${stopId}`)
    .then(res => res.json())
    .then(stopInfo => {
        let stopName = stopInfo.name;
        stopNameDiv.innerText = stopName;

        let busInfo = stopInfo.buses;
        for (const bus of Object.entries(busInfo)) {
            let li = document.createElement('li')
            li.innerText = `Bus ${bus[0]} arrives in ${bus[1]} minutes`

            busesTable.appendChild(li);
        }
    })
    .catch(() => {
        stopNameDiv.innerText = 'Error'
    })
}