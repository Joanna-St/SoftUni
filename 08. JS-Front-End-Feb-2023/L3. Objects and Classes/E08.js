function addressBook(arr) {
    let addressBook = {}
    for (const entry of arr) {
        personName = entry.split(':')[0]
        address = entry.split(':')[1]
        addressBook[personName] = address
    }

    let sorted = Object.entries(addressBook).sort((a, b) => a[0].localeCompare(b[0]))

    for (const entry of sorted) {
        console.log(`${entry[0]} -> ${entry[1]}`);
    }
}

addressBook(['Bob:Huxley Rd',
'John:Milwaukee Crossing',
'Peter:Fordem Ave',
'Bob:Redwing Ave',
'George:Mesta Crossing',
'Ted:Gateway Way',
'Bill:Gateway Way',
'John:Grover Rd',
'Peter:Huxley Rd',
'Jeff:Gateway Way',
'Jeff:Huxley Rd'])