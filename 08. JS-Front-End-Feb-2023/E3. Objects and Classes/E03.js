function storeProvisions(stock, ordered) {
    let finalStock = {};
    let product = '';
    let count = 0;

    for (let i = 0; i < stock.length - 1; i += 2) {
        product = stock[i];
        count = Number(stock[i + 1]);

        finalStock[product] = count;
    }

    for (let i = 0; i < ordered.length - 1; i += 2) {
        product = ordered[i];
        count = Number(ordered[i + 1]);

        if (finalStock.hasOwnProperty(product)) {
            finalStock[product] += count;
        } else {
            finalStock[product] = count;
        }
    }

    for (const product in finalStock) {
        console.log(`${product} -> ${finalStock[product]}`);
    }
}

storeProvisions([
    'Salt', '2', 'Fanta', '4', 'Apple', '14', 'Water', '4', 'Juice', '5'
    ],
    [
    'Sugar', '44', 'Oil', '12', 'Apple', '7', 'Tomatoes', '7', 'Bananas', '30'
    ])