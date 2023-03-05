function fruitsCalc(fruit, grams, price) {
    let kilos = grams/1000;
    console.log(`I need $${(kilos * price).toFixed(2)} to buy ${kilos.toFixed(2)} kilograms ${fruit}.`);
}

fruitsCalc('apple', 1563, 2.35)