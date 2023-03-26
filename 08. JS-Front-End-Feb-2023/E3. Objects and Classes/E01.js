function employees(arr) {
    let employeesArr = [];

    for (const item of arr) {
        let employee = {
            name: item,
            personalNumber: item.length
        }

        employeesArr.push(employee)
    }

    employeesArr.forEach(employee => {
        console.log(`Name: ${employee.name} -- Personal Number: ${employee.personalNumber}`);
    });
}

employees([
    'Silas Butler',
    'Adnaan Buckley',
    'Juan Peterson',
    'Brendan Villarreal'
    ])