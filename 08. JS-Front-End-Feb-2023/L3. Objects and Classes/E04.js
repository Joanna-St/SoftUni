function convertToObject(text) {
    let person = JSON.parse(text);

    let tuples = Object.entries(person);

    for (const tuple of tuples) {
        console.log(`${tuple[0]}: ${tuple[1]}`);
    }
}

convertToObject('{"name": "George", "age": 40, "town": "Sofia"}')