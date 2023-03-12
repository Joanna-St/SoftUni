function sumDigits(num) {
    const isEven = digit => digit % 2 == 0

    let oddSum = 0;
    let evenSum = 0;

    while (num > 0) {
        let digit = num % 10;

        if (isEven(digit)) {
            evenSum += digit;
        } else {
            oddSum += digit;
        }

        num = Math.floor(num / 10);
    }

    return `Odd sum = ${oddSum}, Even sum = ${evenSum}`;
}

console.log(sumDigits(3495892137259234));