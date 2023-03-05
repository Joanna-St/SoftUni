function printAndSum(a, b) {
    let arr = [];
    let sum = 0;
    for (let i = a; i <= b; i++) {
        arr.push(i);
        sum += i;
    }

    console.log(arr.join(' '));
    console.log(`Sum: ${sum}`);
}

printAndSum(5,10)