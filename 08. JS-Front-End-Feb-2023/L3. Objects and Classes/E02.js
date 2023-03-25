function printCity(city) {
    let tuples = Object.entries(city);

    for (const tuple of tuples) {
        console.log(`${tuple[0]} -> ${tuple[1]}`);
    }
}

printCity({
    name: "Sofia",
    area: 492,
    population: 1238438,
    country: "Bulgaria",
    postCode: "1000"
}
)