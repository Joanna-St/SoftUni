function addAndSubtract(a, b, c) {
    const sum = () => a + b;
    const subtract = (summed, c) => summed() - c;

    console.log(subtract(sum, c));
}

addAndSubtract(23,
    6,
    10)