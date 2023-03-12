

function calculator(a, b, operation) {
    const multiply = () => a * b;
    const divide = () => a / b;
    const add = () => a + b;
    const subtract = () => a - b;

    switch (operation) {
        case 'multiply':
            return multiply();
        case 'divide':
            return divide();
        case 'add':
            return add();
        case 'subtract':
            return subtract();
    }
}

console.log(
    calculator(5,
        5,
        'multiply')
);