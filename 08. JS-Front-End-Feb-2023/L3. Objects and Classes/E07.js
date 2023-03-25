function meetings(arr) {
    let schedule = {};

    for (const entry of arr) {
        let weekday = entry.split(' ')[0]
        let person = entry.split(' ')[1]

        if (!schedule.hasOwnProperty(weekday)) {
            schedule[weekday] = person
            console.log(`Scheduled for ${weekday}`);
        } else {
            console.log(`Conflict on ${weekday}!`);
        }
    }

    for (const day in schedule) {
        console.log(`${day} -> ${schedule[day]}`);
    }
}

meetings(['Friday Bob',
'Saturday Ted',
'Monday Bill',
'Monday John',
'Wednesday George'])