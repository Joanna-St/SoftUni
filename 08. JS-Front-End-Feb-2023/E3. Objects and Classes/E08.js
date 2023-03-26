function piccolo(arr) {
    let parkingLot = {
        in (carNum) {
            this[carNum] = 'IN'
        },
        out (carNum) {
            this[carNum] = 'OUT'
        }
    };

    let command = '';
    let carNum = '';

    for (const item of arr) {
        command = item.split(', ')[0].toLowerCase();
        carNum =  item.split(', ')[1];

        parkingLot[command](carNum)
    }

    let carsIn = [];

    for (const entry of Object.entries(parkingLot)) {
        if (entry[1] == 'IN') {
            carsIn.push(entry[0])
        }
    }

    if (carsIn.length > 0) {
        carsIn.sort((num1, num2) => num1.localeCompare(num2)).forEach(entry => console.log(entry));
    } else {
        console.log('Parking Lot is Empty');
    }
}

piccolo(['IN, CA2844AA',
'IN, CA1234TA',
'OUT, CA2844AA',
'OUT, CA1234TA']
)